package com.pingfangx.datastructure.book01.chapter03;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class Stack {
    java.util.Stack<Integer> mStack;

    public Stack() {
        mStack = new java.util.Stack<>();
    }

    public void push(int item) {
        mStack.push(item);
    }

    public int pop() {
        return mStack.pop();
    }

    public boolean empty() {
        return mStack.empty();
    }

    public void clear() {
        mStack.clear();
    }
}
