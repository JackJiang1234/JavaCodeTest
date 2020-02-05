package concurrency.base.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
    public static void main2(String[] args) {
/*
// 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
// 创建CompletionService
        CompletionService<Integer> cs =
                new ExecutorCompletionService<>(executor);
// 用于保存Future对象
        List<Future<Integer>> futures =
                new ArrayList<>(3);
//提交异步任务，并保存future到futures
        futures.add(
                cs.submit(() -> geocoderByS1()));
        futures.add(
                cs.submit(() -> geocoderByS2()));
        futures.add(
                cs.submit(() -> geocoderByS3()));
// 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } finally {
            //取消所有任务
            for (Future<Integer> f : futures)
                f.cancel(true);
        }
// 返回结果
        return r;
        */
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> f1 = executor.submit(()->getPriceByS1());
        Future<Integer> f2 = executor.submit(()->getPriceByS2());
        Future<Integer> f3 = executor.submit(()->getPriceByS3());

        executor.execute(()-> {
            try {
                save(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                save(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                System.out.println(Thread.currentThread().isDaemon());
                save(f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("main done!");
    }

    private static Integer getPriceByS1() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
    private static Integer getPriceByS2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
    private static Integer getPriceByS3() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }
    private static void save(Integer i) {
        System.out.println("save " + i);
    }

}
