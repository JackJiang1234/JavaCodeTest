package ds.backtrace;

import java.util.ArrayList;
import java.util.List;

public class Comb {
    public static void main(String[] args) {
        //comb(new int[]{1, 2, 3, 4}, 2);
        //permutation(new int[]{1, 2, 3, 4}
        f(0, 0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, 53);
        System.out.println(maxW);
    }

    /*
     * Permutation排列和Combination组合 的本质区别在于：决策的顺序对结果有没有影响
     * https://zhuanlan.zhihu.com/p/41855459
     * 将3个奖牌颁发给 8 个人中的, 组合问题
     *
     * */
    private static void comb(int[] people, int prizes) {
        combHelper(new ArrayList<>(), 0, people, prizes);
    }

    private static void combHelper(List<Integer> tmp, int start, int[] people, int kPrize) {
        if (kPrize == 0) {
            System.out.println(tmp);
        } else {
            //people.length - (kPrize - 1)  后面的组合长度已经不可能满足prize数量了，可以排除了
            for (int i = start; i < people.length - (kPrize - 1); i++) {
                tmp.add(people[i]);
                combHelper(tmp, i + 1, people, kPrize - 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private static void permutation(int[] people, int prize) {
        permHelper(new ArrayList<>(), people, prize);
    }

    private static void permHelper(List<Integer> permList, int[] people, int kPrize) {
        if (permList.size() == kPrize) {
            System.out.println(permList);
        } else {
            for (int i = 0; i < people.length; i++) {
                if (!permList.contains(people[i])) {
                    permList.add(people[i]);
                    permHelper(permList, people, kPrize);
                    permList.remove(permList.size() - 1);
                }
            }
        }
    }


    public static int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public static void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        } else {
            //System.out.format("%d,", items[i]);
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
    }
}
