package mutithread.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AqsTest1 {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        for (int i = 0; i < 5; i++) {

            Thread t = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  =>  想要获取锁 ");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "  =>  start ... ");

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } finally {
                    lock.unlock();
                }
            }, "t-" + i);
            t.start();


//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }


    }

//    int CANCELLED = 1//节点从同步队列中取消
//    int SIGNAL = -1//后继节点的线程处于等待状态，如果当前节点释放同步状态会通知后继节点，使得后继节点的线程能够运行；
//    int CONDITION = -2//当前节点进入等待队列中
//    int PROPAGATE = -3//表示下一次共享式同步状态获取将会无条件传播下去
//    int INITIAL = 0;//初始状态


}
