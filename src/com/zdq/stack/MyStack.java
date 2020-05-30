package com.zdq.stack;

import java.util.Stack;

/**
 * 实现一个特殊的栈，实现栈的基本功能，并返回栈中最小元素
 * 要求：
 * 1、pop、push、getMin时间复杂度为O(1)
 * 2、设计的栈类型可以使用现成的栈结构
 *
 * @author ZDQ
 */
public class MyStack {
    public static void main(String[] args) {

    }

    // stack 继承了vector，是线程安全的，deque非线程安全
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty() || this.getMin() >= newNum) {
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("stack is empty!");
        }
        int value = this.stackData.pop();
        if (value == this.getMin()) {
            this.stackMin.pop();
        }
        return value;
    }

    private int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        // 查看栈顶元素，不删除
        return this.stackMin.peek();
    }
}
