package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {

    public void test1() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\test\\code\\utf8.txt");
        PrintStream ps = new PrintStream(fos, true);
        if (ps != null) {
            System.setOut(ps);
        }
        for (int i = 1; i < 255; i++) {
            System.out.print(""+ i+" ");
            if (i % 50 == 0){
                System.out.println();
            }
        }
        ps.close();
    }

    public static void main(String[] args) throws IOException {
        PrintStreamTest test = new PrintStreamTest();
        test.test1();
    }

}
