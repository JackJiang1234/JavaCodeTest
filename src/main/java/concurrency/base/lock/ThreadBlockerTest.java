package concurrency.base.lock;

public class ThreadBlockerTest {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        Thread t1 = new Thread(() -> {
            resource.enter();
        });
        t1.setName("Thread_Jack_test");
        t1.start();
        Thread.sleep(100);
        resource.enter();
    }
}

class Resource {
    public synchronized void enter() {
        System.out.println("enter.");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
