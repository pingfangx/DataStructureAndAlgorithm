package com.pingfangx.datastructure.book01.chapter02;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class LinkNode {
    public Object data;
    public LinkNode next;

    public LinkNode(Object data, LinkNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
