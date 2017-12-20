package com.pingfangx.datastructure.book01.chapter02;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class DbLinkNode {
    public Object data;
    public DbLinkNode prior;
    public DbLinkNode next;

    public DbLinkNode(Object data, DbLinkNode next) {
        this.data = data;
        this.next = next;
    }
}
