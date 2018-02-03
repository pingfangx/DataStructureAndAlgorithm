package com.pingfangx.study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2018/2/3
 */
public class ListTest {
    @Test
    public void testReturnValue() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        //set 和 remove 返回以前在指定位置的元素
        //具体可查看源码
        System.out.println(list.set(0, 9));
        System.out.println(list.remove(0));
        System.out.println(list);
    }
}
