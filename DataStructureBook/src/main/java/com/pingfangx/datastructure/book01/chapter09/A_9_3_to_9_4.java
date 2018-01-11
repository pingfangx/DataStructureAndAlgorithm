package com.pingfangx.datastructure.book01.chapter09;

import com.pingfangx.datastructure.book01.chapter06.BiTree;
import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/12/28
 */
public class A_9_3_to_9_4 {
    void secondOptimal(BiTree t, ElemType[] r, float[] sw, int low, int high) {
        int i = low;
        float min = Math.abs(sw[high] - sw[low]);
        float dw = sw[high] + sw[low - 1];
        for (int j = low + 1; j <= high; j++) {
            if (Math.abs(dw - sw[j] - sw[j - 1]) < min) {
                i = j;
                min = Math.abs(dw - sw[j] - sw[j - 1]);
            }
        }
        t = new BiTree();
        t.data = r[i].key.key;
        if (i == low) {
            t.lchild = null;
        } else {
            secondOptimal(t.lchild, r, sw, low, i - 1);
        }
        if (i == high) {
            t.rchild = null;
        } else {
            secondOptimal(t.rchild, r, sw, i + 1, high);
        }
    }

    STATUS createSOSTree(BiTree t, SSTable st) {
        if (st.length == 0) {
            t = null;
        } else {
            float[] sw = findSw(st);
            secondOptimal(t, st.elem, sw, 1, st.length);
        }
        return STATUS.OK;
    }

    private float[] findSw(SSTable st) {
        return new float[0];
    }
}
