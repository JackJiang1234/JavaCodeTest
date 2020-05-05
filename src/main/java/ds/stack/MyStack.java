package ds.stack;

import javax.sound.midi.SoundbankResource;
import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    private Queue<Integer> masterQueue = new LinkedList<>();
    private Queue<Integer> slaveQueue = new LinkedList<>();
    private int top;
    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        masterQueue.add(x);
        this.top = x;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        while (masterQueue.size() > 1) {
            this.top = masterQueue.remove();
            slaveQueue.add(this.top);
        }

        int pop = masterQueue.remove();
        Queue<Integer> tmp = masterQueue;
        masterQueue = slaveQueue;
        slaveQueue = tmp;

        return pop;
    }

    /**
     * Get the top element.
     */
    public int top() {
        return this.top;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return masterQueue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

