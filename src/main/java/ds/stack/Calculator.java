package ds.stack;

import java.util.Stack;

/*
224
实现一个基本的计算器来计算一个简单的字符串表达式的值。
字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格
* */
public class Calculator {
    private Stack<String> symStack;
    private Stack<String> numStack;

    public Calculator(){
        this.symStack = new Stack<>();
        this.numStack = new Stack<>();
    }

    public int calculate(String s) {

        return 0;
    }
}
