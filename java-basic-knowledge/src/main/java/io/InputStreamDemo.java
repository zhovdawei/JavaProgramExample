package io;

import java.io.*;

public class InputStreamDemo {
    public static void main(String[] args) {
        File f1 = new File("D:\\test\\nice\\test.txt");
        try {
            InputStream is = new FileInputStream(f1);
            byte[] bs = new byte[1024];
            try {
                while (is.read(bs)<0){
                    is.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println(new String(bs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
