package concurrency.base.lock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
*
ReadWriteLock可以提高读取效率：
ReadWriteLock只允许一个线程写入；
ReadWriteLock允许多个线程在没有写入时同时读取；
ReadWriteLock适合读多写少的场景。
* */
public class Counter {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wlock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }

    public int[] get() {
        rlock.lock(); // 加读锁
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rlock.unlock(); // 释放读锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()->{
                counter.inc(1);
            });
            t.start();
        }
        Thread.sleep(1000);
        System.out.println(Arrays.toString(counter.get()));
    }
}