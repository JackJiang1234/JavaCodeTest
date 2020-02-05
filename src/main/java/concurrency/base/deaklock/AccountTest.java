package concurrency.base.deaklock;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class AccountTest {
    private int balance;
    private final Lock lock = new ReentrantLock();

    public AccountTest(int balance) {
        this.balance = 1000;
    }

    public void transfer(AccountTest tar, int amount) throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Random random = new Random();
            if (this.lock.tryLock(random.nextInt(2000), TimeUnit.MILLISECONDS)){
                try {
                    if (tar.lock.tryLock(random.nextInt(5000), TimeUnit.MILLISECONDS)) {
                        try {
                            this.balance -= amount;
                            tar.balance += amount;
                        } finally {
                            tar.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        AccountTest from = new AccountTest(1000);
        AccountTest to = new AccountTest(1000);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    from.transfer(to, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("from balance:" + from.balance);
        System.out.println("to balance:" + to.balance);
        System.out.println("time(mill):" + (start - System.currentTimeMillis()));
    }
}
