package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_5 {
    public static STATUS inorderTraverse_Thr(BiThrTree T) {
        BiThrTree p = (BiThrTree) T.lchild;
        while (p != T) {
            while (p.lTag == BiThrTree.PointerTag.Link) {
                p = (BiThrTree) p.lchild;
            }
            if (A_6_1.visit(p.data) != STATUS.OK) {
                return STATUS.ERROR;
            }
            while (p.rTag == BiThrTree.PointerTag.Thread && p.rchild != T) {
                p = (BiThrTree) p.rchild;
                A_6_1.visit(p.data);
            }
            p = (BiThrTree) p.rchild;
        }
        return STATUS.OK;
    }
}
