package com.pingfangx.datastructure.book01.chapter05;

import com.pingfangx.datastructure.common.structure.ElemType;

/**
 * @author pingfangx
 * @date 2017/12/18
 */
public class A_5_3 {
    //    /**
//     * Q 初始化;
//     * if(Q){//逐行求积
//     *     for(arow=1;arow<=M.mu;arow++){//每一行
//     *         ctemp[]=0;//累加器清零
//     *         //第算 Q 中第 arow 行的积存入 ctemp
//     *         //ctemp[] 中的非零元储存到 Q.data
//     *     }
//     * }
//     */
    public static RLSMatrix MultSMatrix(RLSMatrix M, RLSMatrix N) {
        if (M.nu != N.mu) {
            return null;
        }
        RLSMatrix Q = new RLSMatrix();
        Q.mu = M.mu;
        Q.nu = N.nu;
        Q.tu = 0;
        if (M.tu * N.tu != 0) {
            for (int arrow = 1; arrow <= M.mu; arrow++) {
                int[] ctemp = new int[N.nu + 1];
                //arrow 行的位置是当前数量加 1
                Q.rpos[arrow] = Q.tu + 1;
                //该行最后一个元素
                int tp;
                if (arrow < M.mu) {
                    tp = M.rpos[arrow + 1];
                } else {
                    tp = M.tu + 1;
                }
                for (int p = M.rpos[arrow]; p < tp; p++) {
                    //列对应在 N 中的行号
                    int brow = M.data[p].j;
                    //N 中 brow 的位置
                    int t;
                    if (brow < N.mu) {
                        t = N.rpos[brow + 1];
                    } else {
                        t = N.tu + 1;
                    }
                    // M 中的第 arrow 行，依次乘以 ccol 列，累加到 ctemp[ccol] 中
                    for (int q = N.rpos[brow]; q < t; q++) {
                        int ccol = N.data[q].j;
                        ctemp[ccol] += M.data[p].e.value * N.data[q].e.value;
                    }
                }
                //求得 Q 中第 crow (=arow) 行的非零元
                for (int ccol = 1; ccol <= Q.nu; ccol++) {
                    if (ctemp[ccol] != 0) {
                        if (++Q.tu > RLSMatrix.MAX_SIZE) {
                            return null;
                        }
                        Q.data[Q.tu] = new Triple();
                        Q.data[Q.tu].i = arrow;
                        Q.data[Q.tu].j = ccol;
                        Q.data[Q.tu].e = new ElemType(ctemp[ccol]);
                    }
                }
            }
        }
        Q.rpos = Q.getRPos();
        return Q;
    }
}
