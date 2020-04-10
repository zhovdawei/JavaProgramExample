package mutithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeadLock_1 {

    private final ExecutorService service = Executors.newSingleThreadExecutor();


    public int doTask() throws  Exception{

        Future<Integer> result1;

        result1 = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Future<Integer> result2 = service.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 2;
                    }
                });

                return 1+result2.get();
            }
        });



        return result1.get();

    }


    public static void main(String[] args) {
        DeadLock_1 local = new DeadLock_1();
        try {
            System.out.println(local.doTask());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
