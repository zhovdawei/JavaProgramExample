package mutithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

class MyTask implements Runnable{

    private AtomicLong longer;

    public MyTask(AtomicLong longer){
        this.longer = longer;
    }

    @Override
    public void run() {
        longer.incrementAndGet();
    }
}

public class LatchDemo {

    private static AtomicLong atomicLong = new AtomicLong();


    public static void main(String[] args) {
        int threadNum = (int)(Math.random()*9)+1;
        System.out.println("随机线程数 ： "+threadNum);
        CountDownLatch startDoor = new CountDownLatch(1);
        CountDownLatch endDoor = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        startDoor.await();
                        try {
                            System.out.println(Thread.currentThread().getName()+" 开始执行计算任务...");
                            new MyTask(atomicLong).run();
                        }finally {
                            endDoor.countDown();
                        }
                    } catch (InterruptedException e) { }
                }
            };
            thread.start();
        }

        System.out.println("主线程解开启动闭锁");
        startDoor.countDown();
        try {
            System.out.println("主线程关闭闭锁等待....");
            endDoor.await();
        } catch (InterruptedException e) { }
        System.out.println("总数 : "+atomicLong.toString());

    }

}
