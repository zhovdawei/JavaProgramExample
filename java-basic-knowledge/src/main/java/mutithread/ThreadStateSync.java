package mutithread;

import java.util.concurrent.TimeUnit;

public class ThreadStateSync {

    public static void main(String[] args) {
        final Object obj = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                System.out.println(" t1 getted lock");
                while (true) ;
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                System.out.println(" t2 getted lock");
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
