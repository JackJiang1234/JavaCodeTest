package ds.dp;

import java.util.Arrays;

public class Package {

    public static void main(String[] args) {
        Thing[] things = new Thing[]{
                new Thing(1, 1500),
                new Thing(4, 3000),
                new Thing(3, 2000)
        };

        System.out.println(packageMaxValue(things, 4));
        System.out.println(packageMaxValue2(things, 4));
        System.out.println(packageMaxValue3(things, 4));
    }

    //初始版本
    private static int packageMaxValue(Thing[] things, int packageWeightCap) {
        // 表示从1到要求的背包的容量限制最大值背包可放入的最大价值表格
        int[][] dp = new int[things.length][packageWeightCap];

        //初始化选第一个物品所有背包
        int initValue = things[0].weight <= packageWeightCap ? things[0].value : 0;
        for (int i = 0; i < packageWeightCap; i++) {
            dp[0][i] = initValue;
        }

        //处理剩下的物品选择
        //dp转移方程 max(上一个单元格的价值， 选择当前商品的价值 + 剩余空间的价值)
        for (int i = 1; i < things.length; i++) {
            for (int j = 0; j < packageWeightCap; j++) {
                int selectValue = 0;
                int packageCap = j + 1;
                if (things[i].weight < packageCap) {
                    selectValue = things[i].value + dp[i - 1][packageCap - things[i].weight];
                } else if (things[i].weight == packageCap) {
                    selectValue = things[i].value;
                }

                dp[i][j] = Math.max(dp[i - 1][j], selectValue);
            }
        }

        for (int i = 0; i < things.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[things.length - 1][packageWeightCap - 1];
    }

    //优化版本
    private static int packageMaxValue2(Thing[] things, int packageWeightCap) {
        // 表示从1到要求的背包的容量限制最大值背包可放入的最大价值表格
        int[] dp = new int[packageWeightCap];

        //初始化选第一个物品所有背包
        int initValue = things[0].weight <= packageWeightCap ? things[0].value : 0;
        for (int i = 0; i < packageWeightCap; i++) {
            dp[i] = initValue;
        }

        //dp转移方程 max(前一个单元格的价值， 选择当前商品的价值 + 剩余空间的价值)
        for (int i = 1; i < things.length; i++) {
            for (int j = 0; j < packageWeightCap; j++) {
                int selectValue = 0;
                int packageCap = j + 1;
                if (things[i].weight < packageCap) {
                    selectValue = things[i].value + dp[packageCap - things[i].weight];
                } else if (things[i].weight == packageCap) {
                    selectValue = things[i].value;
                }

                dp[j] = Math.max(dp[j], selectValue);
            }

            System.out.println(Arrays.toString(dp));
        }

        return dp[packageWeightCap - 1];
    }

    private static int packageMaxValue3(Thing[] things, int packageWeightCap) {
        // 表示从1到要求的背包的容量限制最大值背包可放入的最大价值表格
        int[] dp = new int[packageWeightCap + 1];

        //初始化选第一个物品所有背包
        int initValue = things[0].weight <= packageWeightCap ? things[0].value : 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = initValue;
        }

        System.out.println(Arrays.toString(dp));
        //dp转移方程 max(前一个单元格的价值， 选择当前商品的价值 + 剩余空间的价值)
        for (int i = 1; i < things.length; i++) {
            for (int packageCap = things[i].weight; packageCap < dp.length; packageCap++) {
                dp[packageCap] = Math.max(dp[packageCap], things[i].value + dp[packageCap - things[i].weight]);
            }

            System.out.println(Arrays.toString(dp));
        }

        return dp[packageWeightCap];
    }


    private static class Thing {
        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        private int weight;
        private int value;
    }
}
