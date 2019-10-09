package com.pingfangx.study.tutorial.specialized.i18n;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/5/29
 */
public class CharTest {
    @Test
    public void test() {
        char c = '一';
        //是字母，或者说字
        Assert.assertTrue(Character.isLetter(c));
        //其他字符
        Assert.assertEquals(Character.OTHER_LETTER, Character.getType(c));
        //不是大写
        Assert.assertFalse(Character.isUpperCase(c));
        //也不是小写
        Assert.assertFalse(Character.isLowerCase(c));
    }
}
