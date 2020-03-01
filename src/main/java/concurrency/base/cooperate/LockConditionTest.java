package concurrency.base.cooperate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {
    private static int wait = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                while (wait == 0) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Thread.currentThread().setName("1");
                System.out.printf("thread %s done!\n", Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                wait = 1;
                Thread.currentThread().setName("2");
                System.out.printf("thread %s done!\n", Thread.currentThread().getName());
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        });
        t2.start();
    }
}
