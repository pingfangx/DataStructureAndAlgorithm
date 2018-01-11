package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_9_to_9_12 {
    class BSTree {
        ElemType data;
        int bf;
        BSTree lchild;
        BSTree rchild;
    }

    /**
     * 9.9
     */
    void r_rotate(BSTree p) {
        BSTree lc = p.lchild;
        p.lchild = lc.lchild;
        lc.rchild = p;
        p = lc;
    }

    /**
     * 9.10
     */
    void l_rotate(BSTree p) {
        BSTree rc = p.rchild;
        p.rchild = rc.lchild;
        rc.lchild = p;
        p = rc;
    }

    private static final int LH = 1;
    private static final int EH = 0;
    private static final int RH = -1;

    /**
     * 9.11
     */
    STATUS insertAVL(BSTree t, ElemType e, boolean[] taller) {
        if (t == null) {
            t = new BSTree();
            t.data = e;
            t.lchild = null;
            t.rchild = null;
            t.bf = EH;
            taller[0] = true;
        } else {
            if (e.key.EQ(t.data.key)) {
                taller[0] = false;
                return STATUS.FALSE;
            }
            if (e.key.LT(t.data.key)) {
                if (insertAVL(t.lchild, e, taller) == STATUS.FALSE) {
                    return STATUS.FALSE;
                }
                if (taller[0]) {
                    switch (t.bf) {
                        case LH:
                            leftBalance(t);
                            taller[0] = false;
                            break;
                        case EH:
                            t.bf = LH;
                            taller[0] = true;
                            break;
                        case RH:
                            t.bf = EH;
                            rightBalance(t);
                            taller[0] = false;
                            break;
                    }
                }
            }
        }
        return STATUS.OK;
    }

    /**
     * 9.12
     */
    private void leftBalance(BSTree t) {
        BSTree lc = t.lchild;
        switch (lc.bf) {
            case LH:
                t.bf = EH;
                lc.bf = EH;
                r_rotate(t);
                break;
            case RH:
                BSTree rd = lc.rchild;
                switch (rd.bf) {
                    case LH:
                        t.bf = RH;
                        lc.bf = EH;
                        break;
                    case EH:
                        t.bf = EH;
                        lc.bf = EH;
                        break;
                    case RH:
                        t.bf = EH;
                        lc.bf = LH;
                        break;
                }
                rd.bf = EH;
                l_rotate(t.lchild);
                r_rotate(t);
                break;
        }
    }

    private void rightBalance(BSTree t) {
    }
}
