package com.pingfangx.datastructure.book01.chapter06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A_6_16 Tester.
 *
 * @author pingfangx
 * @since 12/21/2017
 */
public class A_6_16Test {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: trial(List<List<Integer>> a, int i, int n)
     */
    @Test
    public void testTrialForAIN() throws Exception {
        int n = 8;
        List<List<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(0);
            }
            a.add(list);
        }
        A_6_16.trial(a, 0, n);
    }


} 
