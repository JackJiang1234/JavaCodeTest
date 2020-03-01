package concurrency.base.cooperate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            putptr++;
            if (putptr == items.length) {
                putptr = 0;
            }
            count++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        Object o;
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            o = items[takeptr];
            takeptr++;
            if (takeptr == items.length) {
                takeptr = 0;
            }
            count--;
            notFull.signalAll();
        } finally {
            lock.unlock();
        }

        return o;
    }

    public static void main(String[] args) {
        BoundBuffer buffer = new BoundBuffer();
        Thread producer = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    buffer.put(i);
                    i++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        producer.start();

        Thread consume = new Thread(()->{
            while (true){
                try {
                    System.out.println(buffer.take());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consume.start();
    }
}
