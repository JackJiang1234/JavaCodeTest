package concurrency.base.cooperate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCountDownLatch {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int n;

    public MyCountDownLatch(int n) {
        this.n = n;
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            while (n != 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void countDown() {
        lock.lock();
        try {
            n -= 1;
            if (n == 0) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
