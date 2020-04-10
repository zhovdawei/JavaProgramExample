package mutithread;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    private final Logger log = Logger.getLogger("TimingThreadPool");

    private final AtomicLong numTasks = new AtomicLong();

    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime,
                            TimeUnit unit,
                            BlockingQueue<Runnable> workQueue){
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
    }

    //执行前
    @Override
    protected void beforeExecute(Thread t,Runnable r){
        super.beforeExecute(t, r);
        log.fine(String.format("Thread %s: start %s",t,r));
        System.out.println(String.format("Thread %s: start %s",t,r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r,Throwable t){
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.fine(String.format("Thread %s : end %s, time=%dns",t,r,taskTime));
            System.out.println(String.format("Thread %s : end %s, time=%dns",t,r,taskTime));
        }finally {
            super.afterExecute(r,t);
        }
    }

    @Override
    protected void terminated(){
        try {
            log.fine(String.format("Terminated avg time=%dns",totalTime.get() / numTasks.get()));
            System.out.println(String.format("Terminated avg time=%dns",totalTime.get() / numTasks.get()));
        }finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ExecutorService service =
                new TimingThreadPool(5,10,5,TimeUnit.SECONDS,workQueue);

        for (int i = 0; i < 1 ; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {

                    int x1 = (int)(Math.random()*8)+1;
                    int x2 = (int)(Math.random()*8)+1;
                    int x3 = (int)(Math.random()*8)+1;
                    int x4 = (int)(Math.random()*8)+1;
                    int x5 = (int)(Math.random()*8)+1;

                    int x = 47/x1/x2/x3/x4/x5;
                }
            });
        }


        service.shutdown();

    }

}
