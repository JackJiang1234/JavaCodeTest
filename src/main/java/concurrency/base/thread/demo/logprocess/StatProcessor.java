package concurrency.base.thread.demo.logprocess;

import java.util.Map;

public interface StatProcessor {
    void process(String record);

    Map<Long, DelayItem> getResult();
}