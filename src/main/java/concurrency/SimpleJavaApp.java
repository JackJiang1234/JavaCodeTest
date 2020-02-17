package concurrency;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleJavaApp {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("this_is_main");
        //Thread.dumpStack();

        Lock lock = new ReentrantLock();
        try{
            lock.lock();
            System.out.println(Thread.holdsLock(lock));
        }
        finally {
            lock.unlock();
        }

        System.out.println(Thread.holdsLock(lock));

        /*
        while (true){
            System.out.println(new Date());
            Thread.sleep(1000);
        }*/
    }
}
