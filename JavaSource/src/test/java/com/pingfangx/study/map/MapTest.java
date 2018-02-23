package com.pingfangx.study.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author pingfangx
 * @date 2018/2/23
 */
public class MapTest {
    class MutableClass {
        private int value;

        public MutableClass(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MutableClass that = (MutableClass) o;

            return value == that.value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return "MutableClass{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 测试将可变对象作为 key 可能出问题
     */
    @Test
    public void testMutableKey() {
        Map<MutableClass, Integer> map = new HashMap<>();
        MutableClass a = new MutableClass(1);
        MutableClass b = new MutableClass(2);
        map.put(a, 1);
        map.put(b, 2);
        //2 个对象
        printMap(map);

        //将 b 的 value 设为 1，此时它和 a 相等(equals)，执行 remove(b)，结果却将 a 移除了
        //不知道这是否说明原文中的“则映射的行为将是不确定的。”，反正就是不要这样做
        b.setValue(1);
        map.remove(b);
        printMap(map);
    }

    /**
     * 测试将 map 自身作为 key,value
     */
    @Test
    public void testSelfKey() {
        Map<Object, Object> map = new HashMap<>();
        map.put(this, null);
        map.put(1, this);
        printMap(map);
    }

    private void printMap(Map map) {
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
