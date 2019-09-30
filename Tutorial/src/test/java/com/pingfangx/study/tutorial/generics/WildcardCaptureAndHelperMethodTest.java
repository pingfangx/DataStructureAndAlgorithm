package com.pingfangx.study.tutorial.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/9/29
 */
public class WildcardCaptureAndHelperMethodTest {
    void test_ObjectListAndUnboundedList() {
        List<Object> objectList = new ArrayList<>();
        List<?> unboundedList = new ArrayList<>();

        objectList.add(1);
        objectList.add(null);

        // 错误
        //unboundedList.add(1);
        //只可以添加 null
        unboundedList.add(null);
    }

    void update(List<?> list) {
        //错误
        //list.set(0, list.get(0));
        updateHelper(list);
    }

    <T> void updateHelper(List<T> list) {
        list.set(0, list.get(0));
    }

    public void test_helperMethod() {
    }
}
