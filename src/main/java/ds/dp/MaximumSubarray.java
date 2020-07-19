package ds.dp;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = sum;

        for (int i = 1; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
