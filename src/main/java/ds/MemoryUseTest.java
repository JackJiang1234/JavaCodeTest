package ds;

public class MemoryUseTest {
    public static void main(String[] args) throws InterruptedException {
        long before = Runtime.getRuntime().totalMemory();
        int[] arr = new int[10000000];
        System.out.format("memory: %d\n", Runtime.getRuntime().totalMemory() - before);
        Thread.sleep(Integer.MAX_VALUE);
        //System.out.println(arr.length);
    }
}
