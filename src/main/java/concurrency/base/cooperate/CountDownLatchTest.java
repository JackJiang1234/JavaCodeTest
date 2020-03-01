package concurrency.base.cooperate;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CountDownLatch latch = new CountDownLatch(n);

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " worked.");
            latch.countDown();
        };

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }

        latch.await();
        System.out.println("main done!");
    }
}
