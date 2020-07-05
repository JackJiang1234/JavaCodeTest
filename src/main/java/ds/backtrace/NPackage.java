package ds.backtrace;

public class NPackage {

    public static void main(String[] args) {
        NPackage p = new NPackage();
        //p.f(0, 0);
        //System.out.println(p.maxW);
        int[] weights = new int[]{2, 2, 4, 6, 3};
        int ret = p.knapsack(weights, 10);
        System.out.println(ret);
    }

    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }

        /*标记[i][cw]已经做过就可以了，因为前面这条路径已计算过，maxW的计算已包含*/
        if (mem[i][cw]) {
            return; // 重复状态
        } else {
            mem[i][cw] = true; // 记录(i, cw)这个状态
        }

        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

    public int knapsack(int[] weight, int w) {
        int n = weight.length;
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    // 不把第i个物品放入背包
                    states[i][j] = true;
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j]) {
                    //将第i个物品放入背包
                    states[i][j + weight[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }

        return 0;
    }

    public int knapsack2(int[] items, int w) {
        int n = items.length;
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }

        return 0;
    }
}
