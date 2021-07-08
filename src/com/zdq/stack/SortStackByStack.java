package com.zdq.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用一个栈实现另一个栈的排序，只允许申请新的变量
 * 从栈顶到栈底按照 从大到小 排序
 *
 * @author ZDQ
 */
public class SortStackByStack {

    public static void main(String[] args) {
     
    }

    private static void sortStackByStack(Deque<Integer> stack) {
        Deque<Integer> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            while (!temp.isEmpty() && temp.peek() < curr) {
                stack.push(temp.pop());
            }
            temp.push(curr);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
}
