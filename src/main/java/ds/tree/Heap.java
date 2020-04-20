package ds.tree;


import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;

public class Heap {
    private int[] a; // 数组，从下标1开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
            swap(a, i, i / 2); // swap()函数作用：交换下标为i和i/2的两个元素
            i = i / 2;
        }
    }


    public void removeMax() {
        if (count == 0) return; // 堆中没有数据
        a[1] = a[count];
        a[count] = 0;
        --count;
        heapify(a, count, 1);
    }


    private void buildHeap(int[] a, int n) {
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }


    // n表示数据的个数，数组a中的数据从下标1到n的位置。
    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }


    private void heapify(int[] a, int n, int i) { // 自上往下堆化
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }



    public static void main(String[] args) throws InvalidAlgorithmParameterException {
        Heap heap = new Heap(10);
        heap.insert(2);
        heap.insert(8);
        heap.insert(10);
        heap.insert(100);

        heap.removeMax();

        System.out.println(Arrays.toString(heap.a));
        heap.makeSureHeap();
    }


    private void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    private void makeSureHeap() throws InvalidAlgorithmParameterException {
        int i = 1;
        int noLeafCount = count / 2;
        while (i * 2 <= noLeafCount) {
            if (a[i] < a[i * 2] || a[i] < a[i * 2 + 1]) {
                throw new InvalidAlgorithmParameterException("not heap");
            }
            i += 1;
        }
    }
}