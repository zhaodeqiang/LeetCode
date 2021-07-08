package com.zdq.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 仅用递归函数和栈操作逆序一个栈
 *
 * @author ZDQ
 */
public class RecursionReverseStack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(2);
        reverseStack(stack);
        System.out.println(stack.peek());
    }

    /**
     * 递归实现删除栈底元素并返回
     *
     * @param stack 栈
     * @return 栈底元素
     */
    private static int getAndRemoveLastElement(Deque<Integer> stack) {
        int pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        int last = getAndRemoveLastElement(stack);
        stack.push(pop);
        return last;
    }

    /**
     * 递归实现逆序栈
     *
     * @param stack 栈
     */
    private static void reverseStack(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int ele = getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(ele);
    }
}
