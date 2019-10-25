package com.pingfangx.study._package.java.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/14
 */
public class IntegerTest {
    @Test
    public void test_highestOneBit() {
        Assert.assertEquals(0, Integer.highestOneBit(0));
        Assert.assertEquals(1, Integer.highestOneBit(1));
        Assert.assertEquals(2, Integer.highestOneBit(0b11));
        Assert.assertEquals(1 << 10, Integer.highestOneBit(0b1_00000_00000));
        Assert.assertEquals(1 << 10, Integer.highestOneBit(0b1_11111_11111));
    }
}
