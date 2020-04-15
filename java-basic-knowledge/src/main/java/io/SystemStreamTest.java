package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SystemStreamTest {

    public void test1() throws IOException{
        System.out.println("请输入信息(退出输入e/exit): \n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s=br.readLine())!=null){
            if("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)){
                System.out.println("安全退出！！！");
                break;
            }
            //将读取到的整行字符串转换成大写输出
            System.out.println("==> : "+s.toUpperCase());
            System.out.println("请继续输入信息：");
        }
        br.close();
    }

    public void test2() throws IOException{
        System.out.println("请输入信息(退出输入e/exit):");
        Scanner scanner = new Scanner(System.in);
        String s = null;
        while (scanner.hasNext()){
            s = scanner.next();
            if("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)){
                System.out.println("安全退出！！！");
                break;
            }
            //将读取到的整行字符串转换成大写输出
            System.out.println("==> : "+s.toUpperCase());
            System.out.println("请继续输入信息：");
        }
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        SystemStreamTest test = new SystemStreamTest();
        test.test2();

    }

}
