package ds.stack;

import java.util.Stack;

/*
* 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
* */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        this.stack.push(x);
        if (this.minStack.empty()) {
            this.minStack.push(x);
        } else if (this.minStack.peek() < x) {
            this.minStack.push(this.minStack.peek());
        } else {
            this.minStack.push(x);
        }
    }

    public void pop() {
        if (!this.stack.empty()) {
            this.stack.pop();
            this.minStack.pop();
        }
    }

    public int top() {
        return this.stack.peek();
    }

    /*检索栈中的最小元素*/
    public int getMin() {
        return this.minStack.peek();
    }
}
