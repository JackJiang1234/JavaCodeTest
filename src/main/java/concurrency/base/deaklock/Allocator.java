package concurrency.base.deaklock;

import java.util.ArrayList;
import java.util.List;

public class Allocator {
    private List<Object> als =
            new ArrayList<>();

    // 一次性申请所有资源
    synchronized boolean apply(
            Object from, Object to) {
        if (als.contains(from) ||
                als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    synchronized void apply2(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {

            }
            als.add(from);
            als.add(to);
        }
    }

    synchronized void free2(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

    // 归还资源
    synchronized void free(
            Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}
