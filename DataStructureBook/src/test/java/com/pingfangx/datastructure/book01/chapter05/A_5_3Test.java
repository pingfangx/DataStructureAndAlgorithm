package com.pingfangx.datastructure.book01.chapter05;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_5_3 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/18/2017</pre>
 */
public class A_5_3Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: MultSMatrix(RLSMatrix M, RLSMatrix N)
     */
    @Test
    public void testMultSMatrix() throws Exception {
        RLSMatrix M = RLSMatrix.createRLSMatrix(new int[][]{
                {1, 1, 3},
                {1, 4, 5},
                {2, 2, -1},
                {3, 1, 2},
        });
        RLSMatrix N = RLSMatrix.createRLSMatrix(new int[][]{
                {1, 2, 2},
                {2, 1, 1},
                {3, 1, -2},
                {3, 2, 4},
                {4, 1, 0}
        });
        LogUtils.d(M);
        LogUtils.d(N);
        RLSMatrix Q = A_5_3.MultSMatrix(M, N);
        LogUtils.d(Q);
    }


} 
