package io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferedExam {

    private final static String PATH = "D:\\test\\book\\";
    private final static String BOOK_NAME = "go.pdf";
    private final static String BIO_NAME = "bio_go.pdf";
    private final static String BUFFER_NAME = "buffer_go.pdf";
    private final static String NIO_NAME = "nio_go.pdf";
    private final static String AIO_NAME = "aio_go.pdf";

    public static long testByte() throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(PATH + BOOK_NAME);
        FileOutputStream fos = new FileOutputStream(PATH + BIO_NAME, true);
        byte[] bs = new byte[1024];
        while (fis.read(bs) > 0) {
            fos.write(bs);
        }
        fos.flush();
        fos.close();
        fis.close();
        return System.currentTimeMillis() - startTime;
    }

    public static long testBuffer() throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(PATH + BOOK_NAME));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(PATH + BUFFER_NAME, true));
        byte[] bs = new byte[1024];
        while (bis.read(bs) > 0) {
            bos.write(bs);
        }
        bos.flush();
        bos.close();
        bis.close();
        return System.currentTimeMillis() - startTime;
    }

    public static long testNio() throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(PATH + BOOK_NAME);
        FileChannel readChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(PATH + NIO_NAME, true);
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (readChannel.read(buffer) > 0) {
            buffer = ByteBuffer.wrap(buffer.array());
            writeChannel.write(buffer);
            buffer.clear();
        }
        readChannel.close();
        writeChannel.close();
        fis.close();
        fos.close();
        return System.currentTimeMillis() - startTime;
    }


    public static void aioWrite(AsynchronousFileChannel writeChannel, ByteBuffer buffer, int index, int len) {
        buffer = ByteBuffer.wrap(buffer.array());
        writeChannel.write(buffer, index * len, "" + index, new CompletionHandler<Integer, String>() {
            @Override
            public void completed(Integer result, String attachment) {
//                System.out.println("【写出】第"+attachment+"次,"+result);
                if (result > 0) {
//                    System.out.println("写出成功 => " + attachment);
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                throw new RuntimeException("【写出失败】index => " + attachment + "," + exc.getCause());
            }
        });
    }

    public static void aioRead(AsynchronousFileChannel readChannel, AsynchronousFileChannel writeChannel, int index, int len) {
        ByteBuffer buffer = ByteBuffer.allocate(len);
        readChannel.read(buffer, index * len, "" + index, new CompletionHandler<Integer, String>() {
            @Override
            public void completed(Integer result, String attachment) {
//                System.out.println("【读入】第"+attachment+"次,"+result);
                if (result > 0) {
//                    System.out.println("读入成功 => " + attachment);
                    aioWrite(writeChannel, buffer, index, len);
                    int indexNew = index + 1;
                    aioRead(readChannel, writeChannel, indexNew, len);
                } else {
                    //无数据读取
                    System.out.println("结束读写，即将释放资源");
                    try {
                        readChannel.close();
                        writeChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                throw new RuntimeException("【读入失败】index => " + attachment + "," + exc.getCause());
            }
        });
    }

    public static long testAio(int index, int len) throws IOException {
        long startTime = System.currentTimeMillis();
        AsynchronousFileChannel readChannel = AsynchronousFileChannel.open(Paths.get(PATH + BOOK_NAME), StandardOpenOption.READ);
        AsynchronousFileChannel writeChannel = AsynchronousFileChannel.open(Paths.get(PATH + AIO_NAME), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        aioRead(readChannel, writeChannel, index, len);
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("普通字节流花费时间："+testByte());
        System.out.println("缓冲字节流花费时间："+testBuffer());
        System.out.println("nio花费时间："+testNio());
        System.out.println("aio花费时间：" + testAio(0, 1024));

        try {
            Thread.sleep(6000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
