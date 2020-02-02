package concurrency.base;

/*可见性测试， 来自极客时间课程例子*/
public class VisibilityTest {

    private int count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    /*缓存导致的可见性问题测试*/
    public static int calc() throws InterruptedException {
        final VisibilityTest test = new VisibilityTest();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();

        return test.count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(VisibilityTest.calc());
    }
}
