package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * 头晕，不知道写的啥
 *
 * @author pingfangx
 * @date 2017/12/20
 */
public class A_6_8_to_6_11 {
    public class MFSet {
        public int n;
        public MFSet[] nodes;
        public int parent;
    }

    /**
     * 6.8
     */
    public int find_mfset(MFSet S, int i) {
        if (i < 1 || i > S.n) {
            return -1;
        }
        int j = i;
        while (S.nodes[j].parent > 0) {
            j = S.nodes[j].parent;
        }
        return j;
    }

    /**
     * 6.9
     */
    public STATUS merge_mfset(MFSet S, int i, int j) {
        if (i < 1 || i > S.n || j < 1 || j > S.n) {
            return STATUS.ERROR;
        }
        //将 i 的 parent 指向 j，这样就完成了并
        S.nodes[i].parent = j;
        return STATUS.OK;
    }

    /**
     * 6.10，需要修改根结点的 parent 存储集合中所含数目的 负值
     */
    public STATUS mix_mfset(MFSet S, int i, int j) {
        if (i < 1 || i > S.n || j < 1 || j > S.n) {
            return STATUS.ERROR;
        }
        if (S.nodes[i].parent > S.nodes[j].parent) {
            S.nodes[j].parent += S.nodes[i].parent;
            S.nodes[i].parent = j;
        } else {
            S.nodes[i].parent += S.nodes[j].parent;
            S.nodes[j].parent = i;
        }
        return STATUS.OK;
    }

    /**
     * 6.11
     */
    public int fixe_mfset(MFSet S, int i) {
        if (i < 1 || i > S.n) {
            return -1;
        }
        int j;
        j = i;
        while (S.nodes[j].parent > 0) {
            j = S.nodes[j].parent;
        }
        int t;
        for (int k = i; k != j; k = t) {
            t = S.nodes[k].parent;
            S.nodes[k].parent = j;
        }
        return j;
    }


}
