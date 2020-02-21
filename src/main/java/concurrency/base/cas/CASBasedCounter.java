package concurrency.base.cas;

import concurrency.base.util.Debug;
import concurrency.base.util.Tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class CASBasedCounter {
    private volatile long count;

    private final AtomicLongFieldUpdater<CASBasedCounter> fieldUpdater;

    public CASBasedCounter() {
        fieldUpdater = AtomicLongFieldUpdater.newUpdater(CASBasedCounter.class, "count");
    }

    public long vaule() {
        return count;
    }

    public void increment() {
        long oldValue;
        long newValue;

        do {
            oldValue = count;
            newValue = oldValue + 1;
        } while (!fieldUpdater.compareAndSet(this, oldValue, newValue));
    }

    public static void main(String[] args) throws InterruptedException {
        final CASBasedCounter counter = new CASBasedCounter();
        Thread t;
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Tools.randomPause(50);
                    counter.increment();
                }
            });
            threads.add(t);
        }

        for (int i = 0; i < 8; i++) {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Tools.randomPause(50);
                    Debug.info(String.valueOf(counter.vaule()));
                }
            });
            threads.add(t);
        }

        Tools.startAndWaitTerminated(threads);
        Debug.info("final count:" + String.valueOf(counter.vaule()));
    }
}
