package com.nowcoder.leetcode.栈;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常量时间内检索最小元素的栈。
 *
 * https://leetcode-cn.com/problems/min-stack/description/
 */
public class Leet155MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if(minStack.empty())
            minStack.push(x);
        else
            minStack.push(Math.min(minStack.peek(),x));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
