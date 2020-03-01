package concurrency.base.cooperate;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                System.out.println(Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("accept interrupt");
                }
            }
        });
        thread.start();

        Thread.sleep(2000);
        thread.interrupt();
    }
}
