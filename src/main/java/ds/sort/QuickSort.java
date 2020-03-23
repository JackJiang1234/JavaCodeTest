package ds.sort;

import java.util.Arrays;

public class QuickSort {

    static void quickSort(int[] a) {
        quickSortImpl(a, 0, a.length - 1);
    }

    static void quickSortImpl(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        System.out.format("%d,%d\n", p, r);

        int q = partition(a, p, r);
        //System.out.println(q);
        quickSortImpl(a, p, q - 1);
        quickSortImpl(a, q + 1, r);
    }

    /*
     * 这里的处理有点类似选择排序。
     * 我们通过游标 i 把 A[p…r-1]分成两部分。A[p…i-1]的元素都是小于 pivot 的，我们暂且叫它“已处理区间”，A[i…r-1]是“未处理区间”。
     * 我们每次都从未处理的区间 A[i…r-1]中取一个元素 A[j]，与 pivot 对比，如果小于 pivot，则将其加入到已处理区间的尾部，也就是 A[i]的位置。
     * */
    public static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;

        for (int j = p; j < r; ++j) {
            if (a[j] > pivot) {
                if (i != j) {
                    swap(a, i, j);
                }
                i++;
            }
        }
        //System.out.println("i:" + i);
        swap(a, i, r);
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 10, 1, 2, 7, 8};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
