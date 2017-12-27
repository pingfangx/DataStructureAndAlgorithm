package com.pingfangx.datastructure.book01.chapter08;

/**
 * @author pingfangx
 * @date 2017/12/27
 */
public class A_8_1 {
    private static final int E = 1;

    public class Space {
        public Space llink;
        public Space uplink;
        int tag;
        int size;
        /**
         * 指向后继结点
         */
        Space rlink;
    }

    Space allocBoundTag(Space pav, int n) {
        Space p = pav;
        //查找不小于 n 的空闲块
        while (p != null && p.size < n && p.rlink != pav) {
            p = p.rlink;
        }
        if (p == null || p.size < n) {
            return null;
        } else {
            Space f = footLoc(p);
            //指向后继
            pav = p.rlink;
            if (p.size - n <= E) {
                if (pav == p) {
                    //变为空表
                    pav = null;
                } else {
                    pav.llink = p.llink;
                    p.llink.rlink = pav;
                }
                p.tag = 1;
                f.tag = 1;
            } else {
                f.tag = 1;
                //剩余块
                p.size -= n;
                f = footLoc(p);
                f.tag = 0;
                f.uplink = p;
//                p=f+1;
                p.tag = 1;
                p.size = n;
            }
        }
        return p;
    }

    private Space footLoc(Space p) {
        return p;
    }
}
