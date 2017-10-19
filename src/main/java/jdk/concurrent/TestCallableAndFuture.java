package jdk.concurrent;

import java.util.concurrent.*;

public class TestCallableAndFuture {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Task task = new Task();
        //1
        Future<Integer> result = pool.submit(task);
        while (!result.isDone()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("task运行结果：" + result.get());
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }
        //2
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        pool.submit(futureTask);

        try {
            while (!futureTask.isDone()) {
                TimeUnit.MILLISECONDS.sleep(5);
            }
            System.out.println("主线程执行任务");
            System.out.println("task运行结果：" + futureTask.get());
        } catch (Exception e) {

        }
        pool.shutdown();
        System.out.println("执行完毕");
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }

}
