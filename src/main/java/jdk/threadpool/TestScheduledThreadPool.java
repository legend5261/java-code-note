package jdk.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor
 * <p>
 * 创建一个定长线程池,支持定时及周期性任务执行.
 * </p>
 *
 * @author pc
 * @since 2016年2月19日 下午2:25:01
 */
public class TestScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟一秒后每3秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);

    }
}
