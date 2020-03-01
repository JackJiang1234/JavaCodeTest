package concurrency.base.cooperate;

public class MyCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        MyCountDownLatch latch = new MyCountDownLatch(n);

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
