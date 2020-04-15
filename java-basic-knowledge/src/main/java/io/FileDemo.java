package io;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) {

        System.out.println("(1)创建文件：存在则不创建：false");
        File f1 = new File("D:\\test\\sth\\newfile.txt");
        try {
            System.out.println(f1.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("(2)创建文件目录");
        File f2 = new File("D:\\test\\bak");
        System.out.println(f2.mkdir());

        System.out.println();

        System.out.println("(3)创建文件目录，上层不存在一并创建了");
        File f3 = new File("D:\\test\\nice\\good\\hha");
        System.out.println(f3.mkdirs());

        System.out.println();

        System.out.println("(4)删除文件目录：Java删除不走回收站，目录中不能存在文件和文件目录");
        File f4 = new File("D:\\test\\nice\\good\\hha");
        System.out.println(f4.delete());

        System.out.println();

        System.out.println("(5)以已存在目录为起点创建目录和文件");
        File target = new File("D:\\test\\nice");
        File f5 = new File(target,"xxx.txt");
        try {
            System.out.println(f5.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f6 = new File(target,"newDir/xxx");
        System.out.println(f6.mkdirs());


    }

}
