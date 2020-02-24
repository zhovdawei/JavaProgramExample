package mutithread;

public class JoinTest {

    public static void main(String[] args) {

        System.out.println("main 线程开始.....");


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                System.out.println("子线程1开始.....");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程1结束.....");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程2开始.....");
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程2休眠.....");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程2结束.....");
            }
        });


        t2.start();
        t1.start();


        System.out.println("main 线程结束.....");



    }

}
