package io;

import java.io.*;

public class BufferedStreamTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\test\\nice\\f1024.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\test\\nice\\out1024.txt"));
        String str;
        while ((str = br.readLine()) != null){
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
