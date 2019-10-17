package com.pingfangx.study.book1.chapter13;

import org.junit.Test;

import java.util.*;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class FormatTest {
    /**
     * 见 Formatter 的文档
     */
    @Test
    public void test() {
        Map<Object, Object> map = new LinkedHashMap<>();
        // bool
        map.put('b', new Object[]{null, true, false, 1, 0});
        // Integer.toHexString(arg.hashCode())
        map.put('h', new Object[]{null, true, 1, "1"});
        // String
        map.put('s', new Object[]{null, true, 1});
        // char
        map.put('c', new Object[]{1, '1', '我'});
        // 十进制
        map.put('d', new Object[]{1, (int) '1'});
        //八进制
        map.put('o', new Object[]{1, 8});
        //十六进制
        map.put('x', new Object[]{1, 0x10});
        //科学计数法
        map.put('e', new Object[]{1.0, (float) (1 << 31)});
        // float
        map.put('f', new Object[]{1.0, (float) (1 << 31)});
        // float 或科学计数法
        map.put('g', new Object[]{1.0, (float) (1 << 31)});
        //十六进制浮点
        map.put('a', new Object[]{1.0, (float) (1 << 31)});
        map.put("tY", new Object[]{new Date(), GregorianCalendar.getInstance()});
        map.put('%', new Object[]{'%'});
        map.put('n', new Object[]{""});

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.println();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value.getClass().isArray()) {
                value = Arrays.asList((Object[]) value);
            }
            if (value instanceof Iterable) {
                for (Object next : (Iterable) value) {
                    String formatResult = String.format("%" + key, next);
                    System.out.printf("format('%%%s',%s)=【%s】 %n", key, next, formatResult);
                }
            } else {
                String formatResult = String.format("%" + key, value);
                System.out.printf("format('%%%s',%s)=【%s】 %n", key, value, formatResult);
            }
        }
    }
}
