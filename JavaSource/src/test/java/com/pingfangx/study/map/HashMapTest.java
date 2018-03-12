package com.pingfangx.study.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2018/3/13
 */
public class HashMapTest {
    @Test
    public void test() {
        Map<Integer, Integer> map = new HashMap<>();
        int[] values = new int[]{
                (1 << 4) - 1,
                1 << 4,
                1 << 5,
                (1 << 5) - 1,
        };
        for (int i : values) {
            map.put(i, i);
            int h;
            int hash = (h = Integer.hashCode(i)) ^ (h >>> 16);
            int index = hash & (16 - 1);
            System.out.println(String.format("key=%s,hash=%s,binary hash=%s,index hash=%s,index=%s", i, hash, Integer.toBinaryString(hash), Integer.toBinaryString(index), index));
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
