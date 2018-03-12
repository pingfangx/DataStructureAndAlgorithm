package com.pingfangx.study.map;

import org.junit.Test;

import java.util.*;

/**
 * @author pingfangx
 * @date 2018/3/13
 */
public class LinkedHashMapTest {
    @Test
    public void test() {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("HashMap 无序");
        iterateMap(map, 100, true);

        System.out.println("LinkedHashMap 插入顺序");
        iterateMap(new LinkedHashMap<>(), 100);

        System.out.println("重新生成一个 map,保持之前的顺序,后面的保持插入顺序");
        Map<Integer, Integer> map2 = new LinkedHashMap<>(map);
        iterateMap(map2, 200);
    }

    private void iterateMap(Map<Integer, Integer> map, int num) {
        iterateMap(map, num, false);
    }

    private void iterateMap(Map<Integer, Integer> map, int num, boolean printIndex) {
        for (int i = 0; i < 10; i++) {
            map.put(num - i, i);
            if (printIndex) {
                int key = num - i;
                int h;
                int hash = (h = Integer.hashCode(key)) ^ (h >>> 16);
                int index = hash & (16 - 1);
                System.out.println(String.format("key=%s,hash=%s,binary hash=%s,index hash=%s,index=%s", key, hash, Integer.toBinaryString(hash), Integer.toBinaryString(index), index));
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }
}
