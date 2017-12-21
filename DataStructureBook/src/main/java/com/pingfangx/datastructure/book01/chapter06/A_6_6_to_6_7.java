package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_6_to_6_7 {
    BiThrTree pre;

    public STATUS inOrderThreading(BiThrTree T) {
        BiThrTree thrt = new BiThrTree();
        thrt.lTag = BiThrTree.PointerTag.Link;
        thrt.rTag = BiThrTree.PointerTag.Thread;
        //右指针回指
        thrt.rchild = thrt;
        if (T != null) {
            thrt.lchild = T;
            pre = thrt;
            inThreading(T);
            pre.rchild = thrt;
            pre.rTag = BiThrTree.PointerTag.Thread;
            thrt.rchild = pre;
        }
        return STATUS.OK;
    }

    /**
     * 6.7 没有测试
     */
    public void inThreading(BiThrTree p) {
        if (p != null) {
            inThreading((BiThrTree) p.lchild);
            if (p.lchild != null) {
                p.lTag = BiThrTree.PointerTag.Thread;
                p.lchild = pre;
            }
            if (pre.rchild != null) {
                pre.rTag = BiThrTree.PointerTag.Thread;
                pre.rchild = p;
            }
            pre = p;
            inThreading((BiThrTree) p.rchild);
        }
    }
}
