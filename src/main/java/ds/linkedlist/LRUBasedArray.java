package ds.linkedlist;

public class LRUBasedArray<T> {
    private final static Integer DEFAULT_CAPACTITY = 10;
    private T[] array;
    private Integer count;

    public LRUBasedArray() {
        this(DEFAULT_CAPACTITY);
    }

    public LRUBasedArray(Integer capacity) {
        this.array = (T[]) new Object[capacity];
        this.count = 0;
    }

    public void add(T data){

    }
}
