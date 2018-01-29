package com.pingfangx.datastructure.book01.chapter10;

/**
 * @author pingfangx
 * @date 2018/1/17
 */
public class SLinkListType {
    private static final int SIZE = 100;

    public static class SLNode {
        Object rc;
        int next;
    }

    SLNode[] r = new SLNode[SIZE];
    int length;
}
