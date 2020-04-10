package mutithread.unit_14;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<T> {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final T[] items = (T[]) new Object[1];
    private int tail,head,count;

    public void put(T t) throws InterruptedException{
        lock.lock();
        try {
            System.out.println("写线程->"+Thread.currentThread().getName()+"  开始执行"+System.nanoTime());
            while (count == items.length){
                System.out.println("写线程->"+Thread.currentThread().getName()+"  进入阻塞"+System.nanoTime());
                notFull.await();
            }
            System.out.println("写线程->"+Thread.currentThread().getName()+"  执行赋值"+System.nanoTime());
            items[tail] = t;
            if(++tail == items.length){
                tail = 0;
            }
            ++count;
            System.out.println("写线程->"+Thread.currentThread().getName()+"  唤醒一个读线程"+System.nanoTime());
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            System.out.println("读线程->"+Thread.currentThread().getName()+"  开始执行"+System.nanoTime());
            while (count == 0){
                System.out.println("读线程->"+Thread.currentThread().getName()+"  进入阻塞"+System.nanoTime());
                notEmpty.await();
            }
            System.out.println("读线程->"+Thread.currentThread().getName()+"  执行取值"+System.nanoTime());
            T t = items[head];
            items[head] = null;
            if(++head == items.length){
                head = 0;
            }
            --count;
            System.out.println("读线程->"+Thread.currentThread().getName()+"  唤醒一个写线程"+System.nanoTime());
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ConditionBoundedBuffer buffer = new ConditionBoundedBuffer();
        final CyclicBarrier barrier = new CyclicBarrier(10);

        for (int i = 0; i < 5; i++) {
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

                    try {
                        buffer.put((int)(Math.random()*1000)+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
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

                    try {
                        System.out.println(buffer.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


}
