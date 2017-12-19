package com.pingfangx.datastructure.book01.chapter05;

import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2017/12/18
 */
public class RLSMatrix extends TSMatrix {
    /**
     * 最大会等于 size
     */
    public static final int MAX_RC = MAX_SIZE;
    public int[] rpos = new int[MAX_RC];

    public static RLSMatrix createRLSMatrix(int[][] data) {
        TSMatrix matrix = create(data);
        RLSMatrix r = new RLSMatrix();
        r.mu = matrix.mu;
        r.nu = matrix.nu;
        r.tu = matrix.tu;
        r.data = matrix.data;
        r.rpos = r.getRPos();
        return r;
    }

    @Override
    public String toString() {
        return super.toString() + Arrays.toString(rpos);
    }
}
