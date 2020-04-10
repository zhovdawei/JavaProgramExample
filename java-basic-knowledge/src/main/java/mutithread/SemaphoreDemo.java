package mutithread;

import java.util.concurrent.Semaphore;

class MyFlag{
    private int age;

    public MyFlag(int i){
        this.age = i;
    }

    public void add(){
        this.age++;
    }

    @Override
    public String toString(){
        return "MyFlag[age="+age+"]";
    }

}

class Handler implements Runnable{

    private final Semaphore semaphore;
    private MyFlag flag;

    public Handler(Semaphore semaphore,MyFlag flag){
        this.semaphore = semaphore;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" 获取到信号量... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            flag.add();
            System.out.println("当前执行线程："+Thread.currentThread().getName());
        }finally {

            semaphore.release();
            System.out.println("当前执行线程："+Thread.currentThread().getName()+" 执行完毕，将释放信号量。 ");
        }
    }
}

public class SemaphoreDemo {

    private final static Semaphore SEMAPHORE = new Semaphore(1);
    private static MyFlag flag = new MyFlag(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Handler(SEMAPHORE,flag)).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(flag);
    }

}
