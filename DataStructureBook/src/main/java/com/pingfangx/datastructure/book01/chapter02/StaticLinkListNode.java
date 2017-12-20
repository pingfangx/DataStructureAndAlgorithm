package com.pingfangx.datastructure.book01.chapter02;

/**
 * 静态链表，使用数组来描述
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class StaticLinkListNode {
    public Object data;
    /**
     * 指针，指向下一个元素的位置
     */
    public int cur;

    public StaticLinkListNode(Object data, int cur) {
        this.data = data;
        this.cur = cur;
    }

    @Override
    public String toString() {
        return String.format("(%s,%d)", data, cur);
    }
}
