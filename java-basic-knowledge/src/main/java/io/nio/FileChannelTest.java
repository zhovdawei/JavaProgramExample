package io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {

    private final static String filepath = "D:\\test\\nio\\file.txt";

    public static void test1() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(filepath));
        System.out.println(new String(data));
    }

    public static void test2() throws IOException {
        Files.write(Paths.get(filepath),new String("周大伟").getBytes(), StandardOpenOption.APPEND);
    }

    public static void main(String[] args) throws IOException {
        test1();
        test2();
        test1();
    }

}
