package io;

import java.io.*;

public class OutputStreamDemo {

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
            OutputStream os = new FileOutputStream("D:\\test\\nice\\t23.txt");
            try {
                os.write(bs);

                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
