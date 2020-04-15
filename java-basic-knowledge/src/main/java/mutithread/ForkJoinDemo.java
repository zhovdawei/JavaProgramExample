package mutithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {

    private static final Integer FLAG = 2;

    private int start, end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= FLAG;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            SumTask sumLeft = new SumTask(start, mid);
            SumTask sumRight = new SumTask(mid + 1, end);
            sumLeft.fork();
            sumRight.fork();
            int leftSum = sumLeft.join();
            int rightSum = sumRight.join();
            sum = leftSum + rightSum;
        }
        return sum;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(1,9);

        Future<Integer> result = pool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
