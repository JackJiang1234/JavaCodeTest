package ds;

import java.util.*;

public class MemoryUseTest {
    public static void main(String[] args) {
        //System.out.println(fourSum(new int[]{-1,0,1,2,-1,-4}, -1));
        //merge(new int[]{0}, 0, new int[]{1}, 1);

        //PriorityQueue
        //System.out.println(largestRectangleArea(new int[]{0, 9}));

        //System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap6(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int findTheLongestSubstring(String s) {
        char[] array = s.toCharArray();
        ArrayList<Character> longestSubString = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {

            }
        }

        return 0;
    }



    public static int trap6(int[] height) {
        int sum = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int current = 0;

        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int h = height[stack.pop()]; //取出要出栈的元素

                while (!stack.isEmpty() && height[stack.peek()] == h) {
                    stack.pop();
                }

                if (stack.isEmpty()) { // 栈空就出去
                    break;
                }

                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);

            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

    public static int trap(int[] height) {
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            //找左边界
            int leftMax = 0;
            for (int left = 0; left < i; left++) {
                leftMax = Math.max(leftMax, height[left]);
            }

            //找右边界
            int rightMax = 0;
            for (int right = i + 1; right < height.length; right++) {
                rightMax = Math.max(rightMax, height[right]);
            }

            int tmp = Math.min(leftMax, rightMax) - height[i];
            if (tmp > 0) {
                sum += tmp;
            }
        }

        return sum;
    }


    public static int largestRectangleArea(int[] heights) {
        //爆力求解
        // 每根柱子遍历求最大面积，
        // 其中最大的面积就是结果
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = heights[0];
        int minHeight;

        for (int i = 0; i < heights.length; i++) {
            minHeight = heights[i];
            int tmpMaxArea = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                if (minHeight > heights[j]) {
                    minHeight = heights[j];
                }
                //System.out.printf("%d, %d\n", minHeight, (j - i + 1));
                tmpMaxArea = Math.max(tmpMaxArea, minHeight * (j - i + 1));
            }

            maxArea = Math.max(maxArea, tmpMaxArea);
        }

        return maxArea;
    }

    public static int largestRectangleArea2(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int h = heights[stack.pop()];
                maxArea = Math.max(maxArea, h * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }

        return maxArea;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length < m + n) {
            return;
        }

        int end = m + n - 1;
        int i = m - 1;
        int j = n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[end] = nums1[i];
                i--;
            } else {
                nums1[end] = nums2[j];
                j--;
            }
            end--;
        }

        while (j >= 0) {
            nums1[end] = nums2[j];
            end--;
            j--;
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Set<List<Integer>> result = new LinkedHashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int v = target - nums[j];
                if (set.contains(v)) {
                    List<Integer> list = Arrays.asList(nums[i], v, nums[j]);
                    list.sort(Comparator.naturalOrder());
                    result.add(list);
                } else {
                    set.add(nums[j]);
                }
                System.out.println("set:" + set);
            }
        }

        return new ArrayList<>(result);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        ArrayList<List<Integer>> lists = new ArrayList<>();
        int len = nums.length;
        for (int a = 0; a < len - 3; a++) {

            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            if (nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;//最小值都大于target
            }

            for (int b = a + 1; b < len - 2; b++) {

                if (b > a + 1 && nums[b] == nums[b - 1]) continue;

                int c = b + 1;
                int d = len - 1;

                while (c < d) {

                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum > target)
                        d--;
                    else if (sum < target)
                        c++;
                    else if (sum == target) {
                        lists.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) c++;
                        while (c < d && nums[d] == nums[d - 1]) d--;
                        c++;
                        d--;
                    }
                }
            }
        }

        return lists;
    }


}
