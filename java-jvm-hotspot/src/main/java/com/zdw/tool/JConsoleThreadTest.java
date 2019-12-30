package com.zdw.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JConsoleThreadTest {

    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("testBusyThread 运行...");
                while (true){
                    ;
                }
            }
        },"testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("testLockThread 运行...");
                synchronized (lock){
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception{
        Thread.sleep(5000);
        System.out.println("main 运行...1");
        createBusyThread();
        Object obj = new Object();
        createLockThread(obj);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }

}
