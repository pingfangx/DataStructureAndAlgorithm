package com.pingfangx.algorithm.encoding.base64;

import com.pingfangx.algorithm.encoding.base64.Base64Demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

/**
 * 测试编码解码
 *
 * @author pingfangx
 * @date 2018/9/17
 */
public class Base64DemoTest {
    @Test
    public void testEncode() {
        Base64.getEncoder().encode("A".getBytes());
        Assert.assertEquals(Base64Demo.encode("A"), "QQ==");
        Assert.assertEquals(Base64Demo.encode("AB"), "QUI=");
        Assert.assertEquals(Base64Demo.encode("ABC"), "QUJD");
    }

    @Test
    public void testDecode() {
        Assert.assertEquals(Base64Demo.decode("QQ=="), "A");
        Assert.assertEquals(Base64Demo.decode("QUI="), "AB");
        Assert.assertEquals(Base64Demo.decode("QUJD"), "ABC");
    }
}