package com.pingfangx.datastructure.common.structure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * HString Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>12/01/2017</pre>
 */
public class HStringTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: length()
     */
    @Test
    public void testLength() throws Exception {
        HString string = new HString("");
        Assert.assertEquals(string.length(), 0);
        string = new HString("12345");
        Assert.assertEquals(string.length(), 5);
    }

    /**
     * Method: compare(HString b)
     */
    @Test
    public void testCompare() throws Exception {
        HString string1 = new HString("");
        HString string2 = new HString("");
        Assert.assertEquals(string1.compare(string2), 0);
    }

    /**
     * Method: sub(int pos, int len)
     */
    @Test
    public void testSub() throws Exception {
        HString string = new HString("12345");
        Assert.assertEquals(string.sub(-1, 1), null);
        Assert.assertEquals(string.sub(5, 1), null);
        Assert.assertEquals(string.sub(0, -1), null);
        Assert.assertEquals(string.sub(0, 6), null);
        Assert.assertEquals(string.sub(0, 5), new HString("12345"));
        Assert.assertEquals(string.sub(0, 0), new HString(""));
        Assert.assertEquals(string.sub(0, 1), new HString("1"));
        Assert.assertEquals(string.sub(4, 1), new HString("5"));
    }

} 
