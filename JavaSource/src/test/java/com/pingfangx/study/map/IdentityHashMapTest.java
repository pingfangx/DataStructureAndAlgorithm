package com.pingfangx.study.map;

import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2018/3/14
 */
public class IdentityHashMapTest {
    @Test
    public void test() {
        Map<String, Integer> map = new IdentityHashMap<>();
        map.put("a", 1);
        map.put("a", 2);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println();
        map.put(new String("a"), 3);
        map.put(new String("a"), 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
