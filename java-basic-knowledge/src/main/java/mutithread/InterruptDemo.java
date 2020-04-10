package mutithread;

class InterruptAble implements Runnable{

    private volatile boolean interruptFlag = false;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 运行开始... ");

        for (int i = 0; i < 10; i++) {
            if(interruptFlag){
                break;
            }

            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("第"+(i+1)+"次循环...");
        }
    }

    public void shutdown(){
        this.interruptFlag = true;
    }

}

public class InterruptDemo {

    public static void main(String[] args) {
        InterruptAble able = new InterruptAble();

        new Thread(able).start();

        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        able.shutdown();
    }

}
