package ds.backtrace;


import java.util.Arrays;

public class KString {
    public static void main(String[] args) {
        kString(3, 3);
    }

    private static void kString(int n, int k) {
        output = new int[n];
        generateKString(n, k);
    }

    static int[] output;

    private static void generateKString(int n, int k) {
        if (n < 1) {
            System.out.println(Arrays.toString(output));
        } else {
            for (int j = 0; j < k; j++) {
                output[n - 1] = j;
                generateKString(n - 1, k);
            }
        }
    }
}
