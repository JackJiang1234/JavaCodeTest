package ds.stack;

import java.util.Stack;

/*
使用栈实现队列的下列操作：

push(x) -- 将一个元素放入队列的尾部。
pop() -- 从队列首部移除元素。
peek() -- 返回队列首部的元素。
empty() -- 返回队列是否为空。
* */
class MyQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        this.stackPush.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (this.stackPop.isEmpty()) {
            this.secondPush();
        }
        return this.stackPop.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (this.stackPop.isEmpty()) {
            this.secondPush();
        }
        return this.stackPop.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return this.stackPush.empty() && this.stackPop.empty();
    }

    private void secondPush() {
        while (!this.stackPush.isEmpty()) {
            this.stackPop.push(this.stackPush.pop());
        }
    }
}
