package com.pingfangx.datastructure.book01.chapter06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A_6_14_to_6_15 Tester.
 *
 * @author pingfangx
 * @since 12/20/2017
 */
public class A_6_14_to_6_15Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: getPowerSet(int i, List<Integer> a, List<Integer> b)
     */
    @Test
    public void testGetPowerSet() throws Exception {
        List<Integer> a = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            a.add(i);
        }
        List<Integer> b = new ArrayList<>();
        A_6_14_to_6_15.getPowerSet(0, a, b);
    }

}
