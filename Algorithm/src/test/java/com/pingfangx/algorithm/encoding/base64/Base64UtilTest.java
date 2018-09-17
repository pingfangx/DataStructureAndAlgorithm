package com.pingfangx.algorithm.encoding.base64;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2018/9/17
 */
public class Base64UtilTest {
    @Test
    public void testEncode() {
        Base64Util.Encoder encoder = new Base64Util.Encoder();
        Assert.assertEquals(encoder.encode("A"), "QQ==");
        Assert.assertEquals(encoder.encode("AB"), "QUI=");
        Assert.assertEquals(encoder.encode("ABC"), "QUJD");
        Assert.assertEquals(encoder.encode("ABCA"), "QUJDQQ==");
    }

    @Test
    public void testDecode() {
        Base64Util.Decoder decoder=new Base64Util.Decoder();
        Assert.assertEquals(decoder.decode("QQ=="), "A");
        Assert.assertEquals(decoder.decode("QUI="), "AB");
        Assert.assertEquals(decoder.decode("QUJD"), "ABC");
    }

}