package concurrency.base;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/*可见性测试， 来自极客时间课程例子*/
public class VisibilityTest {

    private int count = 0;
    private AtomicInteger atomicCount = new AtomicInteger(0);

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    private void add10K2() {
        int idx = 0;
        while (idx++ < 10000) {
            atomicCount.getAndIncrement();
        }
    }

    /*缓存导致的可见性问题测试*/
    public static int calc() throws InterruptedException {
        final VisibilityTest test = new VisibilityTest();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.add10K2();
        });
        Thread th2 = new Thread(() -> {
            test.add10K2();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();

        return test.atomicCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(VisibilityTest.calc());
    }
}
