package ds.problem;

import java.util.ArrayList;
import java.util.Arrays;

/*
https://leetcode-cn.com/problems/sliding-window-maximum/
*/
public class MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            window.push(nums[i]);
            if (i >= k - 1) {
                res.add(window.max());
                // i - k + 1 窗口最后的元素
                window.pop(nums[i - k + 1]);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
