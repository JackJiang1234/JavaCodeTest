package concurrency.base.cooperate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CyclicBarrier barrier = new CyclicBarrier(n, ()->{
            System.out.println("addition action");
        });

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

        Thread.sleep(500);

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
}
}
