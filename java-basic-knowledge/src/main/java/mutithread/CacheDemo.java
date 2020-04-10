package mutithread;

import java.math.BigInteger;
import java.util.concurrent.*;

interface Calculate<A,V>{

    V cal(A arg) throws InterruptedException;

}

class CalculateImpl implements Calculate<String,BigInteger>{
    @Override
    public BigInteger cal(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}

public class CacheDemo<A,V> implements Calculate<A,V>{

    private final ConcurrentHashMap<A, Future<V>> cache =
            new ConcurrentHashMap<>();

    private final Calculate<A,V> cimpl;

    public CacheDemo(Calculate<A,V> cal){
        this.cimpl = cal;
    }

    @Override
    public V cal(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if( f == null){
            System.out.println(Thread.currentThread().getName()+" -> 无缓存，构建缓存...");
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return cimpl.cal(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = cache.putIfAbsent(arg,ft);
            if(f == null){
                f = ft;
                ft.run();
            }
        }else {
            System.out.println(Thread.currentThread().getName()+" -> 有缓存，读取缓存值...");
        }
        try {
            return f.get();
        }catch (CancellationException e){
            cache.remove(arg,f);
            throw new RuntimeException(e.getCause());
        }catch (ExecutionException e){
            throw new RuntimeException(e.getCause());
        }
    }

    public static void main(String[] args) {
        CacheDemo<String,BigInteger> demo = new CacheDemo<>(new CalculateImpl());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 50; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+" 同步结束... ");

                    try {
                        Thread.sleep(((int)Math.random()*9+1)*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        BigInteger biger = demo.cal("24562");
                        System.out.println(biger.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            t.start();
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
