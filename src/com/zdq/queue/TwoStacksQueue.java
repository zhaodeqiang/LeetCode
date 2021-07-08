package com.zdq.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 两个栈实现一个队列
 *
 * @author ZDQ
 */
public class TwoStacksQueue {
    private final Deque<Integer> stackPush;
    private final Deque<Integer> stackPop;

    public TwoStacksQueue() {
        stackPush = new ArrayDeque<>();
        stackPop = new ArrayDeque<>();
    }

    private void pushToPop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt) {
        stackPush.push(pushInt);
        pushToPop();
    }

    public int poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }
        pushToPop();
        Integer peek = stackPop.peek();
        return peek == null ? -1 : peek;
    }

    public static void main(String[] args) {

    }
}
