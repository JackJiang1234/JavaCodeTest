package concurrency.base.cooperate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        int n = 5;
        BlockingQueue<String> channel = new LinkedBlockingQueue<>(n);

        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    channel.put("hello");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    System.out.println(channel.take());
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();

        //SynchronousQueue
    }
}
