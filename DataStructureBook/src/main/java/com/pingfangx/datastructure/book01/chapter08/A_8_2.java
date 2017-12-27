package com.pingfangx.datastructure.book01.chapter08;

/**
 * @author pingfangx
 * @date 2017/12/27
 */
public class A_8_2 {
    class Space {
        Space llink;
        int tag;
        int kval;
        Space rlink;
    }

    class HeadNode {
        int nodeSize;
        Space first;
    }

    Space allocBuddy(HeadNode[] avail, int n) {
        int m = avail.length;
        int k = 0;
        while (k <= m && (avail[k].nodeSize < n + 1 || avail[k].first == null)) {
            k++;
        }
        if (k > m) {
            return null;
        } else {
            Space pa = avail[k].first;
            Space pre = pa.llink;
            Space suc = pa.rlink;
            if (pa == suc) {
                avail[k].first = null;
            } else {
                pre.rlink = suc;
                suc.llink = pre;
                avail[k].first = suc;
            }
            int i;
            for (i = 1; avail[k - i].nodeSize >= n + 1; i++) {
                Space pi = new Space();
//                pi = pa + Math.pow(2, k - 1);
                pi.rlink = pi;
                pi.llink = pi;
                pi.tag = 0;
                pi.kval = k - i;
                avail[k - i].first = pi;
            }
            pa.tag = 1;
            pa.kval = k - (--i);
            return pa;
        }
    }
}
