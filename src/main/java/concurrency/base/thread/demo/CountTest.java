package concurrency.base.thread.demo;

import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.concurrent.*;

/*多线程统计测试*/
public class CountTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long upper = 1000000000L;
        singleThreadTest(upper);
        multiThreadTest(upper);
    }

    static void singleThreadTest(long upper) {
        long start = System.currentTimeMillis();
        System.out.println("single thread test.");
        System.out.printf("sum: %d\n", add(0, upper));
        System.out.printf("time spent %d mill.\n", System.currentTimeMillis() - start);
    }

    static void multiThreadTest(long upper) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        int nthread = Runtime.getRuntime().availableProcessors() + 1;
        long span = upper / nthread;
        long lower = 0;
        ExecutorService executor = Executors.newFixedThreadPool(nthread);

        HashSet<Future<Long>> set = new HashSet<>();
        for (int i = 0; i < nthread; i++) {
            long l = lower;
            long u = lower + span;
            set.add(executor.submit(() -> add(l, u)));
            lower += span;
        }

        long sum = 0;
        for (Future<Long> task : set) {
            sum += task.get();
        }

        System.out.println("multi thread test.");
        System.out.printf("sum: %d\n", sum);
        System.out.printf("time spent %d mill.\n", System.currentTimeMillis() - start);
    }

    static long add(long from, long to) {
        long sum = 0;
        for (long i = from; i < to; i++) {
            sum += i;
        }
        return sum;
    }
}
