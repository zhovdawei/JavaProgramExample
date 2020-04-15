package io;

import java.io.*;

public class DataStreamTest {

    public void test1() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\test\\code\\data.txt"));
        dos.writeUTF("徐皮皮是个大猪皮！！！");
        dos.writeBoolean(true);
        dos.writeLong(Long.parseLong("11111111111111111"));
        dos.close();
    }

    public void test2() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("D:\\test\\code\\data.txt"));
        System.out.println(dis.readUTF());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readLong());
        dis.close();
    }

    public static void main(String[] args) throws IOException {
        DataStreamTest test = new DataStreamTest();
        test.test1();
        test.test2();
    }

}
