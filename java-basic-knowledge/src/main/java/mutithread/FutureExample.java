package mutithread;

import java.util.concurrent.*;


class Example{

    private final String num;
    private final Future<Long> future;

    public Example(String num,Future<Long> future){
        this.num = num;
        this.future = future;
    }

    public String getNum(){
        return num;
    }

    public Future<Long> getFuture(){
        return future;
    }


/*    private final Long number;

    public Example(String num,Long number){
        this.num = num;
        this.number = number;
    }

    @Override
    public String toString(){
        return "Example[num"+num+" , number="+number.toString()+"]";
    }*/

}

public class FutureExample {

    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    public Example create(String number){
        Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Thread.sleep(1000*5);
                return Long.parseLong(number);
            }
        };

        Future<Long> future = executor.submit(task);
        /*Long value = null;
        try {
            value = future.get();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            future.cancel(true);
        }catch (ExecutionException e){
            throw new RuntimeException(e.getCause());
        }*/
        Example example = new Example(number,future);
        return example;
    }


    public static void main(String[] args) {
        FutureExample futureExample = new FutureExample();
        Example example = futureExample.create("20200226");
        System.out.println(example.getNum());
        System.out.println("===========================");
        try {
            System.out.println(example.getFuture().get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            System.out.println(example.getFuture().isDone());
        }
        System.out.println(Thread.currentThread().getState());
        futureExample.executor.shutdown();
    }

}
