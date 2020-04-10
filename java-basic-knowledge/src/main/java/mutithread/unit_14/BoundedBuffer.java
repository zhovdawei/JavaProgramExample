package mutithread.unit_14;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public BoundedBuffer(int size){
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" 进入执行..."+System.nanoTime());
        while (isFull()){
            System.out.println(Thread.currentThread().getName()+" 即将进入wait ");
            wait();
        }
        System.out.println(Thread.currentThread().getName()+" 即将推送数据..."+System.nanoTime());
        doPut(v);
        System.out.println(Thread.currentThread().getName()+" 即将推送完毕..."+System.nanoTime());
        notifyAll();
    }

    public synchronized V take() throws InterruptedException{
        while (isEmpty()){
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(10);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(1);

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
                        System.out.println(Thread.currentThread().getName()+"取元素 : "+buffer.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }



    }


}
