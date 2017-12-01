package com.pingfangx.datastructure.common.structure;

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
