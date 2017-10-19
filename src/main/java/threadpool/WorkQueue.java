package threadpool;

import java.util.LinkedList;

public class WorkQueue {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList<Runnable> queue;

    public WorkQueue(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new PoolWorker[nThreads];
        this.queue = new LinkedList<Runnable>();

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized (queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    r = (Runnable) queue.removeFirst();
                }
                try {
                    r.run();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
