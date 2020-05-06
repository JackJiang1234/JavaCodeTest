package ds.problem;

import java.util.Arrays;

/*
https://leetcode-cn.com/problems/sliding-window-maximum/
*/
public class MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            window.push(nums[i]);
            if (i >= k - 1) {
                res[index++] = window.max();
                // i - k + 1 窗口最后的元素
                window.pop(nums[i - k + 1]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
