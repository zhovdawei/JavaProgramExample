package mutithread.aqs;

import java.util.concurrent.Semaphore;

public class AqsSharedTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 5; i++) {

            Thread t = new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 已获取共享锁... ");

                    String tname = Thread.currentThread().getName();
                    if(tname.equals("T:0")){
                        Thread.sleep(15000);
                    }else {
                        Thread.sleep(600000000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+" 即将释放锁... ");
                    semaphore.release();
                }
            },"T:"+i);
            t.start();

        }





    }


}
