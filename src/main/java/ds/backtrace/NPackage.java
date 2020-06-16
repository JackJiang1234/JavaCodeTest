package ds.backtrace;

public class NPackage {

    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    /*
    *
    * 对于每个物品来说，都有两种选择，装进背包或者不装进背包。
    * 对于 n 个物品来说，总的装法就有 2^n 种，去掉总重量超过 Wkg 的，
    * 从剩下的装法中选择总重量最接近 Wkg 的
    * */

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }


}
