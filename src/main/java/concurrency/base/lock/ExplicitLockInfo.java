package concurrency.base.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitLockInfo {
    private static final Lock lock = new ReentrantLock();
    private static int sharedData = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            lock.lock();
            try {
                try {
                    Thread.sleep(2200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        });
        t.start();
        Thread.sleep(100);
        lock.lock();
        try {
            System.out.println("sharedDate:" + sharedData);
        } finally {
            lock.unlock();
        }
    }
}
