package ds.greedy;

public class LemonadeChange {

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5}));
    }

    public static boolean lemonadeChange(int[] bills) {
        //遍历bills, 统计记录剩下的5,10块的数量，找零先从10块开始，
        int fiveCount = 0;
        int tenCount = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
            } else if (bills[i] == 10) {
                if (fiveCount > 0) {
                    fiveCount--;
                } else {
                    return false;
                }
                tenCount++;
            } else if (bills[i] == 20) {
                int change = 15;
                if (tenCount > 0) {
                    change -= 10;
                    tenCount--;
                }
                while (change > 0 && fiveCount > 0){
                    change -= 5;
                    fiveCount--;
                }
                if (change > 0){
                    return false;
                }
            }
        }

        return true;
    }
}
