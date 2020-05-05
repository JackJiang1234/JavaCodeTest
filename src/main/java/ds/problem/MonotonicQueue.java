package ds.problem;

import java.util.ArrayDeque;

public class MonotonicQueue {
    private ArrayDeque<Integer> dequeue = new ArrayDeque<>();

    /// 在队尾添加元素 n
    public void push(int n){
        while (!dequeue.isEmpty() && dequeue.peekLast() < n){
            dequeue.removeLast();
        }
        dequeue.addLast(n);
    }

    //返回当前队列中的最大值
    public int max(){
        return dequeue.getFirst();
    }

    //队头元素如果是 n，删除它
    void pop(int n){
        if (!dequeue.isEmpty() && dequeue.getFirst() == n){
            dequeue.removeFirst();
        }
    }
}
