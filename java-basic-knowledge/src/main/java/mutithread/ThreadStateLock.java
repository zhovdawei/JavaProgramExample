package mutithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateLock {

    public static void main(String[] args) {
        final Object obj = new Object();
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(" t1 getted lock");
                int x = 0 ;
                while (x<100){
                    x = x + 5;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(" t2 getted lock");
                int x= 0;
                while (x<50){
                    x = x + 5;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        },"t2");


        t1.start();;
        sleep(2);
        t2.start();

        while (true){
            System.out.println("t1 start => "+t1.getState());
            System.out.println("t2 start => "+t2.getState());
            sleep(1);
        }

    }

    private static void sleep(long time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
