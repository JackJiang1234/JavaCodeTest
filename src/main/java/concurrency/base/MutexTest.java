package concurrency.base;

public class MutexTest {

}


class Account {
    // 锁：保护账户余额
    private final Object balLock
            = new Object();
    // 账户余额
    private Integer balance;
    // 锁：保护账户密码
    private final Object pwLock
            = new Object();
    // 账户密码
    private String password;

    // 取款
    void withdraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }

    // 查看余额
    Integer getBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    // 更改密码
    void updatePassword(String pw) {
        synchronized (pwLock) {
            this.password = pw;
        }
    }

    // 查看密码
    String getPassword() {
        synchronized (pwLock) {
            return password;
        }
    }
}


class Account2 {
    private Object lock;
    private int balance;

    private Account2() {
    }

    // 创建Account时传入同一个lock对象
    public Account2(Object lock) {
        this.lock = lock;
    }

    // 转账
    void transfer(Account2 target, int amt) {
        // 此处检查所有对象共享的锁
        synchronized (lock) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}


class Account3 {
    private int balance;

    // 转账
    void transfer(Account3 target, int amt) {
        synchronized (Account3.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}

/*可能产生死锁
*
* 死锁条件
* 互斥，共享资源 X 和 Y 只能被一个线程占用；
* 占有且等待，线程 T1 已经取得共享资源 X，在等待共享资源 Y 的时候，不释放共享资源 X；
* 不可抢占，其他线程不能强行抢占线程 T1 占有的资源；
* 循环等待，线程 T1 等待线程 T2 占有的资源，线程 T2 等待线程 T1 占有的资源，就是循环等待。
*
* */
class Account4 {
    private int balance;

    // 转账
    void transfer(Account4 target, int amt) {
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
    }
}