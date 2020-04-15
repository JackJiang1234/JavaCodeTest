package ds.sort;

public class FindKthLargestTest {

    public int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, k);
    }

    private int find(int[] nums, int start, int end, int k) {
        int p = QuickSort.partition(nums, start, end);

        if (p + 1 > k) {
            return find(nums, start, p - 1, k);
        } else if (p + 1 < k) {
            return find(nums, p + 1, end, k);
        } else {
            return nums[p];
        }
    }

    public static void main(String[] args) {
        FindKthLargestTest test = new FindKthLargestTest();
        System.out.println(test.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(test.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(test.findKthLargest(new int[]{89, 100, 101, 102},3));
    }
}
