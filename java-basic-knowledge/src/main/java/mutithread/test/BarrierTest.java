package mutithread.test;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BarrierTimer implements Runnable{

    private boolean started;
    private long startTime,endTime;

    @Override
    public synchronized void run() {
        long t = System.nanoTime();
        System.out.println(t);
        if(!started){
            System.out.println("11111111");
            started = true;
            startTime = t;
        }else {
            System.out.println("2222222222222");
            endTime = t;
        }
    }

    public synchronized void clear(){
        started = false;
    }

    public synchronized long getTime(){
        return endTime - startTime;
    }
}
public class BarrierTest {

    public static void main(String[] args) {
        BarrierTimer timer = new BarrierTimer();
        CyclicBarrier barrier = new CyclicBarrier(4,timer);
        timer.clear();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println("子线程执行睡眠1s");
                    try {
                        Thread.sleep(1234);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程结束睡眠");

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        long ns = timer.getTime();
        System.out.println(ns);

        System.out.println(ns/3);


    }

}
