package com.pingfangx.datastructure.book01.chapter05;

/**
 * @author pingfangx
 * @date 2017/12/4
 */
public class A_5_1_to_5_2 {
    /**
     * 转置矩阵
     * O(mu*tu)
     */
    public static TSMatrix transposeSMatrix(TSMatrix M) {
        TSMatrix T = new TSMatrix();
        T.mu = M.nu;
        T.nu = M.mu;
        T.tu = M.tu;
        if (T.tu > 0) {
            int q = 1;
            //按列转置，M 按列生成的结果就相当于 T 按行
            for (int col = 1; col <= M.nu; col++) {
                //遍历查找，如果 j==列，则转置
                for (int p = 1; p <= M.tu; p++) {
                    if (M.data[p].j == col) {
                        T.data[q] = new Triple();
                        T.data[q].i = M.data[p].j;
                        T.data[q].j = M.data[p].i;
                        T.data[q].e = M.data[p].e;
                        q++;
                    }
                }
            }
        }
        return T;
    }

    /**
     * 快速转置
     * num[col] 表示 M 中 col 列的非零元个数
     * cpot[col] 表示 M 中第 col 列第一个非零元在 b.data 中的恰当位置
     */
    public static TSMatrix fastTransposeSMatrix(TSMatrix M) {
        TSMatrix T = new TSMatrix();
        T.mu = M.nu;
        T.nu = M.mu;
        T.tu = M.tu;
        if (T.tu > 0) {
            int[] cpot = M.getCPos();
            for (int p = 1; p <= M.tu; p++) {
                int col = M.data[p].j;
                int q = cpot[col];
                T.data[q] = new Triple();
                T.data[q].i = M.data[p].j;
                T.data[q].j = M.data[p].i;
                T.data[q].e = M.data[p].e;
                //已经暂用了一个位置，对其进行 ++
                cpot[col]++;
            }
        }
        return T;
    }
}
