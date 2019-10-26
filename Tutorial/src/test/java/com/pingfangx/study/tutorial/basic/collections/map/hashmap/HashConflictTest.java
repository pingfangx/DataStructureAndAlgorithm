package com.pingfangx.study.tutorial.basic.collections.map.hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/25
 */
public class HashConflictTest {

    @Test
    public void test() {
        List<Object> objectList = createConflictObjectList();
        for (int i = 0; i < objectList.size() - 1; i++) {
            test_hash(objectList.get(i), objectList.get(i + 1));
        }
    }

    public static List<Object> createConflictObjectList() {
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            objectList.add((1L << (32 + i)) + (1L << i));
        }
        return objectList;
    }

    private void test_hash(Object o1, Object o2) {
        System.out.println();
        System.out.printf("o1=%s, o2=%s %n", o1, o2);
        if (o1 instanceof Long) {
            System.out.printf("o1.toBinaryString()=%s, o2.toBinaryString()=%s %n", toBinaryString((Long) o1, 64), toBinaryString((Long) o2, 64));
        }
        System.out.printf("o1.hashCode()=%s, o2.hashCode()=%s %n", o1.hashCode(), o2.hashCode());
        System.out.printf("HashMap.hash(o1)=%s, HashMap.hash(o2)=%s %n", hash(o1), hash(o2));
        System.out.printf("o1.equals(o2)=%s %n", o1.equals(o2));
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private String toBinaryString(long value, int bits) {
        return String.format("%" + bits + "s", Long.toBinaryString(value))
                .replace(" ", "0");
    }
}
