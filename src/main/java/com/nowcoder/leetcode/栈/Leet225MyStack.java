package com.nowcoder.leetcode.栈;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用队列实现栈
 *
 * @link https://leetcode-cn.com/problems/implement-stack-using-queues/description/
 * 将队列看成一个数组.
 **/
public class Leet225MyStack {
    private int index = -1;
    private List<Integer> list = new ArrayList<>();

    /** Push element x onto stack. */
    public void push(int x) {
        index ++ ;
        list.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(index < 0)
            return 0;
        return list.remove(index--);
    }

    /** Get the top element. */
    public int top() {
        if(index < 0)
            return 0;
        return list.get(index);
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return index < 0;
    }
}
