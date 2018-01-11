package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_7_to_9_8 {
    STATUS deleteBST(BiTree t, KeyType key) {
        if (t == null) {
            return STATUS.FALSE;
        } else {
            if (key.EQ(t.data.key)) {
                return delete(t);
            } else if (key.LT(t.data.key)) {
                return deleteBST(t.lchild, key);
            } else {
                return deleteBST(t.rchild, key);
            }
        }
    }

    STATUS delete(BiTree p) {
        BiTree q;
        if (p.rchild == null) {
            q = p;
            p = p.lchild;
            q = null;
        } else if (p.lchild == null) {
            q = p;
            p = p.rchild;
            q = null;
        } else {
            q = p;
            BiTree s = p.lchild;
            while (s.rchild != null) {
                q = s;
                s = s.rchild;
            }
            p.data = s.data;
            if (q != p) {
                q.rchild = s.lchild;
            } else {
                q.lchild = s.lchild;
            }
            s = null;
        }
        return STATUS.OK;
    }
}
