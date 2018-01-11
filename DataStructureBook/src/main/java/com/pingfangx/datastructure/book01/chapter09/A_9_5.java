package com.pingfangx.datastructure.book01.chapter09;


import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_5 {
    BiTree p = null;

    BiTree searchBST(BiTree t, KeyType key) {
        if (t == null || key.EQ(t.data.key)) {
            return t;
        } else if (key.LT(t.data.key)) {
            return searchBST(t.lchild, key);
        } else {
            return searchBST(t.rchild, key);
        }
    }

    STATUS searchBST(BiTree t, KeyType key, BiTree f) {
        if (t == null) {
            p = f;
            return STATUS.FALSE;
        } else if (key.EQ(t.data.key)) {
            p = t;
            return STATUS.TRUE;
        } else if (key.LT(t.data.key)) {
            return searchBST(t.lchild, key, t);
        } else {
            return searchBST(t.rchild, key, t);
        }
    }

    STATUS insertBST(BiTree t, ElemType e) {
        p = null;
        if (searchBST(t, e.key, null) == STATUS.FALSE) {
            BiTree s = new BiTree();
            s.data = e;
            s.lchild = null;
            s.rchild = null;
            if (p == null) {
                t = s;
            } else if (e.key.LT(p.data.key)) {
                p.lchild = s;
            } else {
                p.rchild = s;
            }
            return STATUS.TRUE;
        } else {
            return STATUS.FALSE;
        }
    }
}
