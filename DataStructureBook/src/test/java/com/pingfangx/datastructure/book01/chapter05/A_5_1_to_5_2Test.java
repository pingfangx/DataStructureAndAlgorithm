package com.pingfangx.datastructure.book01.chapter05;

import com.pingfangx.datastructure.common.util.LogUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A_5_1_to_5_2 Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/18/2017</pre>
 */
public class A_5_1_to_5_2Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: transposeSMatrix(TSMatrix M)
     */
    @Test
    public void testTransposeSMatrix() throws Exception {
        TSMatrix matrix = TSMatrix.create(new int[][]{
                {1, 2, 12},
                {1, 3, 9},
                {3, 1, -3},
                {3, 6, 14},
                {4, 3, 24},
                {5, 2, 18},
                {6, 1, 15},
                {6, 4, -7},
                {6, 7, 0},
        });
        LogUtils.d(matrix);
        LogUtils.d(A_5_1_to_5_2.transposeSMatrix(matrix));
    }


    /**
     * Method: fastTransposeSMatrix(TSMatrix M)
     */
    @Test
    public void testFastTransposeSMatrix() throws Exception {
        TSMatrix matrix = TSMatrix.create(new int[][]{
                {1, 2, 12},
                {1, 3, 9},
                {3, 1, -3},
                {3, 6, 14},
                {4, 3, 24},
                {5, 2, 18},
                {6, 1, 15},
                {6, 4, -7},
                {6, 7, 0},
        });
        LogUtils.d(matrix);
        LogUtils.d(A_5_1_to_5_2.fastTransposeSMatrix(matrix));
    }


} 
