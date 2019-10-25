package com.pingfangx.study.tutorial.basic.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class BinarySearchTest {
    /**
     * pos = -insertPos-1
     * pos + 1 = -insertPos
     * -pos - 1 = insertPos
     * <p>
     * x=-y-1
     * y=-x-1
     * 如 -3=-2-1
     * 2=-(-3)-1
     */
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 2);
        }
        System.out.println(list);
        int num = 3;
        int pos = Collections.binarySearch(list, num);
        System.out.println("pos " + pos);
        if (pos < 0) {
            int insertPos = -pos - 1;
            System.out.println("insert position " + insertPos);
            list.add(insertPos, num);
            System.out.println(list);
        }
    }
}
