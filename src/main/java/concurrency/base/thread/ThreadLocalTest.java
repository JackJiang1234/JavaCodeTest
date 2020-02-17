package concurrency.base.thread;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> System.out.println(ThreadId.get())).start();

        new Thread(()-> System.out.println(ThreadId.get())).start();
        new Thread(() -> System.out.println(Thread.currentThread().isDaemon())).start();
    }

    static class ThreadId {
        static final AtomicLong
                nextId = new AtomicLong(0);
        //定义ThreadLocal变量
        static final ThreadLocal<Long>
                tl = ThreadLocal.withInitial(
                () -> nextId.getAndIncrement());

        //此方法会为每个线程分配一个唯一的Id
        static long get() {
            return tl.get();
        }
    }
}
