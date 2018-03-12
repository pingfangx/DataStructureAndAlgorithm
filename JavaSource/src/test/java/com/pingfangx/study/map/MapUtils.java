package com.pingfangx.study.map;

import java.util.Map;

/**
 * @author pingfangx
 * @date 2018/3/14
 */
public class MapUtils {
    public static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            printEntry(entry);
        }
    }

    public static void printEntry(Map.Entry entry) {
        System.out.println(entry.getKey() + "=" + entry.getValue());
    }
}
