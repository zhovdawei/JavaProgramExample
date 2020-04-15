package io;

import java.io.*;

public class CharStreamTest {

    public static void main(String[] args) {

        String target = "徐皮皮是个放屁精！";

        try {
            Writer wr = new FileWriter(new File("D:\\test\\nice\\xpp.txt"));
            wr.write(target);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            FileReader reader = new FileReader("D:\\test\\nice\\xpp.txt");
            char[] chrs = new char[1024];
            try {
                while (reader.read(chrs)<0){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println(new String(chrs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
