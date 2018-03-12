package com.pingfangx.study.map;

import org.junit.Test;

import java.util.Objects;

/**
 * @author pingfangx
 * @date 2018/3/12
 */
public class HashCodeTest {
    @Test
    public void test() {
        for (float i = 1; i < 100; i++) {
            hashAndPrint(i);
        }
    }

    private void hashAndPrint(float o) {
        System.out.println();
        System.out.println(o);
        System.out.println(Integer.toBinaryString((int) o));
        int hash = Objects.hashCode(o);
        System.out.println(Integer.toBinaryString(hash));
        System.out.println(hash);
    }
}
