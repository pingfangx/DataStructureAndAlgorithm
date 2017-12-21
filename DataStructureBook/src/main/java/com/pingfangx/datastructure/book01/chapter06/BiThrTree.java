package com.pingfangx.datastructure.book01.chapter06;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class BiThrTree extends BiTree {
    public static enum PointerTag {
        Link, Thread;//指针、线索
    }

    public PointerTag lTag;
    public PointerTag rTag;
}
