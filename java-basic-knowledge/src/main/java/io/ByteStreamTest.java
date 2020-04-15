package io;

import java.io.*;

public class ByteStreamTest {

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream(new File("D:\\test\\nice\\opu.txt"),true);
        String target = "行首 => ";
        for (int i = 0; i < 5; i++) {
            String str = new String(target+i+"\n");
            os.write(str.getBytes());
        }

        os.flush();
        os.close();

    }

}
