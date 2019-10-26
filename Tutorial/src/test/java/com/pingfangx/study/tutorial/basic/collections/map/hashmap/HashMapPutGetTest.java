package com.pingfangx.study.tutorial.basic.collections.map.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2019/10/26
 */
public class HashMapPutGetTest {
    public Map<Object, Object> createMap() {
        List<Object> conflictObjectList = HashConflictTest.createConflictObjectList();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < conflictObjectList.size(); i++) {
            map.put(conflictObjectList.get(i), "第" + (i + 1) + "次添加");
        }
        return map;
    }

    @Test
    public void test_put() {
        createMap();
    }

    @Test
    public void test_get() {
        Map<Object, Object> map = createMap();
        for (Object o : HashConflictTest.createConflictObjectList()) {
            System.out.println(map.get(o));
        }
    }
}
