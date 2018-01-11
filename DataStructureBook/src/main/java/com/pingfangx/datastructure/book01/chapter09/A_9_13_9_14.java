package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_13_9_14 {
    private static final int m = 3;

    class BTree {
        int keynum;
        BTree parent;
        KeyType[] key = new KeyType[m + 1];
        BTree[] ptr = new BTree[m + 1];
        BTree[] recptr = new BTree[m + 1];
    }

    class Result {
        BTree pt;
        /**
         * 1..m 在结点中的关键字序号
         */
        int i;
        /**
         * 1 表成功
         * 0 表失败
         */
        int tag;

        public Result(BTree pt, int i, int tag) {
            this.pt = pt;
            this.i = i;
            this.tag = tag;
        }
    }

    Result searchBTree(BTree t, KeyType k) {
        BTree p = t;
        BTree q = null;
        boolean found = false;
        int i = 0;
        while (p != null && !found) {
            i = search(p, k);
            if (i > 0 && p.key[i] == k) {
                found = true;
            } else {
                q = p;
                p = p.ptr[i];
            }
        }
        if (found) {
            return new Result(p, i, 1);
        } else {
            return new Result(q, i, 0);
        }
    }

    private int search(BTree p, KeyType k) {
        return 0;
    }

    /**
     * 9.14
     */
    STATUS insertBTree(BTree t, KeyType k, BTree q, int i) {

        return STATUS.OK;
    }
}
