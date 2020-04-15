package io;

import java.io.*;

public class ChangeStreamDemo {

    private final static String readPath = "D:\\test\\code\\utf8.txt";
    private final static String writePath = "D:\\test\\code\\gbk.txt";

    public static void test1() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath), "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(writePath),"gbk");
        char[] chars = new char[1024];
        while (isr.read(chars)>0){
            osw.write(chars);
        }
        osw.flush();
        osw.close();
        isr.close();
    }
    public static void test2() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath), "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(writePath),"gbk");
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        String str;
        while ((str=br.readLine())!=null){
            bw.write(str);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }



    public static void main(String[] args) throws IOException {
        test2();
    }
}
