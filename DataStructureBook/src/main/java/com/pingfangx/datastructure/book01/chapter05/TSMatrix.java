package com.pingfangx.datastructure.book01.chapter05;

/**
 * 三元组顺序表
 *
 * @author pingfangx
 * @date 2017/12/18
 */
public class TSMatrix {
    public static final int MAX_SIZE = 12500;
    /**
     * 非空三元组表，data[0]未用
     */
    public Triple[] data = new Triple[MAX_SIZE];
    /**
     * 矩阵行数
     */
    public int mu;
    /**
     * 矩阵列数
     */
    public int nu;
    /**
     * 矩阵非零元个数
     */
    public int tu;

    public static TSMatrix create(int[][] data) {
        TSMatrix matrix = new TSMatrix();
        if (data != null) {
            int length = data.length;
            //非零元个数
            matrix.tu = length;
            if (length > 0) {
                int[] last = data[data.length - 1];
                //取最后一个元素的 i 即为行数
                matrix.mu = data[data.length - 1][0];
                //取第一个元素的长度即为列数
                matrix.nu = 1;
                for (int i = 0; i < length; i++) {
                    //列数，取最大的
                    if (matrix.nu < data[i][1]) {
                        matrix.nu = data[i][1];
                    }
                    matrix.data[i + 1] = Triple.create(data[i]);
                }
            }
        }
        return matrix;
    }

    /**
     * num[col] 表示 M 中 col 列的非零元个数
     * cpot[col] 表示 M 中第 col 列第一个非零元在 b.data 中的恰当位置
     */
    public int[] getCPos() {
        int[] num = new int[nu + 1];
        for (int col = 1; col <= nu; col++) {
            num[col] = 0;
        }
        for (int t = 1; t < tu; t++) {
            //列中非零个数
            num[data[t].j]++;
        }
        int cpos[] = new int[nu + 1];
        cpos[1] = 1;
        for (int col = 2; col <= nu; col++) {
            cpos[col] = cpos[col - 1] + num[col - 1];
        }
        return cpos;
    }

    /**
     * num[col] 表示 M 中 col 列的非零元个数
     * cpot[col] 表示 M 中第 col 列第一个非零元在 b.data 中的恰当位置
     * 这里在 5.2 下面的文字中说明
     * “为此，可将上节快速转置矩阵算法中创建的，指示“行”信息的辅助数组 cpot 固定在稀疏矩阵的存储结构中”
     * 这里是用的 M 求的 M 的列信息，对应 T 的行信息，要储存在 T 中应该用 T 求才行。
     * 所以被误导了，其实还是需要 2 个方法。
     */
    public int[] getRPos() {
        int[] num = new int[mu + 1];
        for (int row = 1; row <= mu; row++) {
            num[row] = 0;
        }
        for (int t = 1; t < tu; t++) {
            //行中非零个数
            num[data[t].i]++;
        }
        int rpos[] = new int[mu + 1];
        rpos[1] = 1;
        for (int row = 2; row <= mu; row++) {
            rpos[row] = rpos[row - 1] + num[row - 1];
        }
        return rpos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("mu=%d,nu=%d,tu=%d\n", mu, nu, tu));
        for (int i = 1; i < data.length; i++) {
            Triple triple = data[i];
            if (triple == null) {
                break;
            }
            builder.append(triple);
            builder.append('\n');
        }
        return builder.toString();
    }
}
