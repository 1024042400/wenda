package com.nowcoder.leetcode.队列;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @link https://leetcode-cn.com/problems/implement-queue-using-stacks/description/
 *
 * 用俩个栈来实现.
 */
public class Leet232MyQueue {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!s2.empty()) {
            return s2.pop();
        } else {
            putS1ToS2();
            if(!s2.empty())
                return s2.pop();
        }
        return 0;
    }

    /** Get the front element. */
    public int peek() {
        if(!s2.empty()) {
            return s2.peek();
        } else {
            putS1ToS2();
            if(!s2.empty())
                return s2.peek();
        }
        return 0;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }

    private void putS1ToS2() {
        while(!s1.empty()) {
            s2.push(s1.pop());
        }
    }
}
