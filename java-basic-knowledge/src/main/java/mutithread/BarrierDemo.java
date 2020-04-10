package mutithread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class CountNum implements Runnable{

    private final CyclicBarrier barrier;

    private final int id;

    public CountNum(CyclicBarrier barrier,int id){
        this.barrier = barrier;
        this.id = id;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" 将休眠"+100*id+"ms");
            Thread.sleep(100*id);
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 完成第一次同步");


            System.out.println(Thread.currentThread().getName()+" 将休眠"+(1000-100*id)+"ms");
            Thread.sleep(1000-100*id);
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 完成第二次同步");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BarrierDemo {

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Thread(new CountNum(cyclicBarrier,i)).start();
        }
    }

}
