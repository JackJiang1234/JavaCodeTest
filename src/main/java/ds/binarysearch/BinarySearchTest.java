package ds.binarysearch;

import java.util.Arrays;

public class BinarySearchTest {

    /*查找第一个值等于给定值的元素*/
    public static int searchFirstEqual(int[] nums, int val) {
        return binarySearch(nums, 0, nums.length - 1, val);
    }

    private static int binarySearch(int[] nums, int low, int high, int val) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > val) {
                high = mid - 1;
            } else if (nums[mid] < val) {
                low = mid + 1;
            } else if (low == 0 || nums[mid - 1] != val) {
                return mid;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /*查找最后一个值等于给定值的元素*/
    public static int searchLastEqual(int[] nums, int val) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > val) {
                high = mid - 1;
            } else if (nums[mid] < val) {
                low = mid + 1;
            } else if (mid == nums.length - 1 || nums[mid + 1] != val) {
                return mid;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /*查找第一个大于等于给定值的元素*/
    public static int searchFirstGreat(int[] nums, int val) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] >= val) {
                if (mid == 0 || nums[mid - 1] < val) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /*查找最后一个小于等于给定值的元素*/
    public static int searchFirstLess(int[] nums, int val) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] <= val) {
                if (mid == nums.length - 1 || nums[mid + 1] > val) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /*如何快速定位IP对应的省份地址？
     * 当我们要查询某个 IP 归属地时，我们可以先通过二分查找，
     * 找到最后一个起始 IP 小于等于这个 IP 的 IP 区间，
     * 然后，检查这个 IP 是否在这个 IP 区间内，如果在，我们就取出对应的归属地显示；
     * 如果不在，就返回未查找到
     * */
    public static String findIpLocation(String ip) {


        return null;
    }

    /*
     * 我们今天讲的都是非常规的二分查找问题，今天的思考题也是一个非常规的二分查找问题。如果有序数组是一个循环有序数组，比如 4，5，6，1，2，3。
     * 针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
     *
     * 我们发现循环数组存在一个性质：以数组中间点为分区，会将数组分成一个有序数组和一个循环有序数组。
     * 如果首元素小于 mid，说明前半部分是有序的，后半部分是循环有序数组；
     * 如果首元素大于 mid，说明后半部分是有序的，前半部分是循环有序的数组；
     * 如果目标元素在有序数组范围中，使用二分查找；
     * 如果目标元素在循环有序数组中，设定数组边界后，使用以上方法继续查找。
     * */
    public static int findEqual(int[] nums, int target) {
        return search(nums, target);
    }

    private static int search(int[] nums, int target) {
        if (nums.length == 1 && nums[0] == target) return 0;
        int low = 0;
        int high = nums.length - 1;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;
            if (nums[low] == target)
                return low;
            if (nums[high] == target)
                return high;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] > nums[low]) {
                //mid和low之间数据是有序的

                if (target > nums[mid]) {
                    // 数据不在mid 和 low之间
                    low = mid + 1;
                } else {

                    if (target > nums[low]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            } else {
                if (target > nums[mid]) {
                    if (target < nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    high = mid - 1;
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(searchFirstEqual(new int[]{1, 2, 3, 4, 5, 6, 8, 8, 8, 9}, 8));
        //System.out.println(searchLastEqual(new int[]{1, 2, 3, 4, 5, 6, 8, 8, 8, 9}, 8));
        //System.out.println(searchLastEqual(new int[]{1, 1, 1, 1}, 1));

        //System.out.println(searchFirstGreat(new int[]{1, 1, 1, 1}, 1));
        //System.out.println(searchFirstGreat(new int[]{1, 2, 5, 8}, 4));
        //System.out.println(searchFirstLess(new int[]{1, 1, 1, 1}, 1));
        //System.out.println(searchFirstLess(new int[]{1, 2, 6, 9}, 7));
        //System.out.println( 192L << 24 + 168L << 16 + 1L << 8 + 1L);
        //System.out.println(16 << 1);
        //intToIp();
        //ipToInt();


        System.out.println(findEqual(new int[]{1, 3, 5}, 3));
        System.out.println(findEqual(new int[]{3, 1}, 1));
        System.out.println(findEqual(new int[]{1, 3}, 3));
        System.out.println(findEqual(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(findEqual(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    private static void intToIp() {
        long ipl = 1852797041l;

        long A = ipl >> 24;
        long B = (ipl & 0x00FFFFFF) >> 16;
        long C = (ipl & 0x0000FFFF) >> 8;
        long D = ipl & 0x000000FF;

        String ip = new StringBuffer().append(A).append(".").append(B).append(".")
                .append(C).append(".").append(D).toString();
        System.out.println(ip);
    }

    private static void ipToInt() {
        String ip = "110.111.112.113";
        String[] s = ip.split("\\.");
        System.out.println(Arrays.toString(s));
        long ipl = (Long.parseLong(s[0]) << 24) + (Long.parseLong(s[1]) << 16)
                + (Long.parseLong(s[2]) << 8) + (Long.parseLong(s[3]));

        System.out.println(ipl);
    }
}
