package jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPoolExecutor
 * <p>
 * 创建一个定长线程池,可控制线程最大并发数,超出的线程会在队列中等待.
 * </p>
 *
 * @author pc
 * @since 2016年2月19日 下午2:18:07
 */
public class TestFixedThreadPool {
    public static void main(String[] args) {
        final int nThreadPool = Runtime.getRuntime().availableProcessors();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreadPool);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
