package concurrency.base.deaklock;


public class Account {
    private Allocator actr;
    private int balance;
    private int id;

    // 破坏占用且等待条件
    void transfer(Account target, int amt) {
        // 一次性申请转出账户和转入账户，直到成功
        while (!actr.apply(this, target)) {
        }
        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }

    // 破坏循环等待条件
    void transfer2(Account target, int amt) {
        Account left = this;
        Account right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }
        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大的账户
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
