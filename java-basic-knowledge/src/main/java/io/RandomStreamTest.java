package io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomStreamTest {

    public static void test1() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("D:\\test\\code\\random.txt","rw");
        raf.seek(5);
        byte[] bytes = new byte[1024];
        int off = 0;
        int len = 5;
        raf.read(bytes,off,len);

        String str = new String(bytes,0,len);
        System.out.println(str);
        raf.close();
    }

    public static void test2() throws IOException{
        RandomAccessFile raf = new RandomAccessFile("D:\\test\\code\\random.txt","rw");
        raf.seek(5);
        String wirteStr = new String("zdw");
        raf.write(wirteStr.getBytes(),0,3);
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        test1();
        test2();
    }

}
