package com.pingfangx.study.book1.chapter03;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class ByteCacheInitTest {

    private static class ByteCache {
        private ByteCache() {
            System.out.println("ByteCache()");
        }

        static final Byte cache[] = new Byte[-(-128) + 127 + 1];

        static {
            System.out.println("static");
            for (int i = 0; i < cache.length; i++)
                cache[i] = new Byte((byte) (i - 128));
        }
    }

    @Test
    public void test() {
        /*
         * test
         * static
         * -128
         * test2
         */
        System.out.println("test");
        System.out.println(ByteCache.cache[0]);
        System.out.println("test2");
    }
}
