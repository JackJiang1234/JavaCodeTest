package ds.problem;

import java.util.Arrays;
import java.util.HashMap;

public class AddSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> checkedMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (checkedMap.containsKey(target - nums[i])) {
                return new int[]{i, checkedMap.get(target - nums[i])};
            } else {
                checkedMap.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}
