package concurrency.base.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockUsage {
    final StampedLock s1 = new StampedLock();

    void readTemplate() {
        long stamp = s1.tryOptimisticRead();
        //读入局部变量

        //校验stamp
        if (!s1.validate(stamp)) {
            //升级为悲观读锁
            stamp = s1.readLock();
            try {
                // 读入局部变量
            } finally {
                // 释放悲观读锁
                s1.unlockRead(stamp);
            }
        }

        //使用方法局部变量执行业务操作
    }

    void writeTemplate() {
        long stamp = s1.writeLock();
        try {
            // 写共享变量
        } finally {
            s1.unlockRead(stamp);
        }
    }
}
