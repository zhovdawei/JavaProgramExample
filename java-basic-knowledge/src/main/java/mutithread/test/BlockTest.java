package mutithread.test;

import java.beans.IntrospectionException;

public class BlockTest {

    void testTakeBlocksWhenEmpty(){
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){
            @Override
            public void run() {
                try {
                    int unused = bb.take();
                    System.out.println("这一行不该执行.....");
                }catch (InterruptedException success){ }
            }
        };

        try {
            taker.start();
            Thread.sleep(1000*10);
            taker.interrupt();
            taker.join(1000*10);
            System.out.println(taker.isAlive());
        }catch (InterruptedException e){

        }

    }

    public static void main(String[] args) {
        BlockTest test = new BlockTest();
        test.testTakeBlocksWhenEmpty();
    }


}
