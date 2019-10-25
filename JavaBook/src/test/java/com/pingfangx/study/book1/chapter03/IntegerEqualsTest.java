package com.pingfangx.study.book1.chapter03;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class IntegerEqualsTest {
    @Test
    public void test() {
        Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        Assert.assertNotSame(n1, n2);
        Assert.assertEquals(n1, n2);
        Assert.assertNotSame(new Integer(47), new Integer(47));
        //new 是新建对象，但是 valueOf 有缓存
        Assert.assertSame(Integer.valueOf(47), Integer.valueOf(47));
    }
}
