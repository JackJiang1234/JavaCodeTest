package concurrency.base.cas;


class SimulatedCAS {
    int count;

    synchronized int cas(
            int expect, int newValue) {
        // 读目前count的值
        int curValue = count;
        // 比较目前count值是否==期望值
        if (curValue == expect) {
            // 如果是，则更新count的值
            count = newValue;
        }
        // 返回写入前的值
        return curValue;
    }

    void addOne() {
        int newValue;
        do {
            newValue = count + 1; //①
        } while (count != cas(count, newValue));
    }
}
