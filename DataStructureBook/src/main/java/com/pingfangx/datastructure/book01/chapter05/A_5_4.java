package com.pingfangx.datastructure.book01.chapter05;

import com.pingfangx.datastructure.common.structure.ElemType;
import com.pingfangx.datastructure.common.util.IOUtils;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/12/18
 */
public class A_5_4 {
    public static void main(String[] args) {
        //m=3,n=4,t=4
        //113,145,22-1,312
        CrossList crossList = createSMatrix();
        LogUtils.d(crossList);
    }

    public static class OLNode {
        int i;
        int j;
        ElemType e;
        OLNode right;
        OLNode down;

        public StringBuilder getRow() {
            StringBuilder builder = new StringBuilder();
            builder.append(e);
            if (right != null) {
                builder.append('-');
                builder.append('>');
                builder.append(right.getRow());
            }
            return builder;
        }

        public StringBuilder getColumn() {
            StringBuilder builder = new StringBuilder();
            builder.append(e);
            if (down != null) {
                builder.append('↘');
                builder.append(down.getColumn());
            }
            return builder;
        }
    }

    public static class CrossList {
        OLNode[] rhead;
        OLNode[] chead;
        int mu;
        int nu;
        int tu;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("m=%d,n=%d,t=%d\n", mu, nu, tu));
            builder.append("rhead\n");
            for (OLNode node : rhead) {
                if (node != null) {
                    builder.append(node.getRow());
                } else {
                    builder.append("null");
                }
                builder.append('\n');
            }
            builder.append("chead\n");
            for (OLNode node : chead) {
                if (node != null) {
                    builder.append(node.getColumn());
                } else {
                    builder.append("null");
                }
                builder.append('\n');
            }
            return builder.toString();
        }
    }

    public static CrossList createSMatrix() {
        CrossList M = new CrossList();
        int m = IOUtils.getInt("m");
        M.mu = m;
        int n = IOUtils.getInt("n");
        M.nu = n;
        M.tu = IOUtils.getInt("t");
        M.rhead = new OLNode[m + 1];
        M.chead = new OLNode[n + 1];
        int i = IOUtils.getInt("i");
        int j = IOUtils.getInt("j");
        int e = IOUtils.getInt("e");
        while (i != 0) {
            OLNode p = new OLNode();
            p.i = i;
            p.j = j;
            p.e = new ElemType(e);
            //为空，或者比当前 列 大，插在前面
            if (M.rhead[i] == null || M.rhead[i].j > j) {
                p.right = M.rhead[i];
                M.rhead[i] = p;
            } else {
                //查找
                OLNode q = M.rhead[i];
                while (q.right != null && q.right.j < j) {
                    q = q.right;
                }
                p.right = q.right;
                q.right = p;
            }
            //列
            if (M.chead[j] == null || M.chead[j].i > i) {
                p.down = M.chead[j];
                M.chead[j] = p;
            } else {
                OLNode q = M.chead[j];
                while (q.down != null && q.down.i < i) {
                    q = q.down;
                }
                p.down = q.down;
                q.down = p;
            }
            i = IOUtils.getInt("i");
            j = IOUtils.getInt("j");
            e = IOUtils.getInt("e");
        }
        return M;
    }
}
