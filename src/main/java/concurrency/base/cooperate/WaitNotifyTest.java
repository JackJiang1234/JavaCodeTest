package concurrency.base.cooperate;

public class WaitNotifyTest {
    private  static int wait = 0;

    public static void main(String[] args) {
        Object lock = new Object();
        wait = 0;
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (wait == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.holdsLock(lock));
                Thread.currentThread().setName("1");
                System.out.printf("thread %s done!\n", Thread.currentThread().getName());
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                wait = 1;
                Thread.currentThread().setName("2");
                System.out.printf("thread %s done!\n", Thread.currentThread().getName());
                lock.notifyAll();
            }
        });
        t2.start();

        System.out.println("main thread:" + Thread.holdsLock(lock));
    }
}

