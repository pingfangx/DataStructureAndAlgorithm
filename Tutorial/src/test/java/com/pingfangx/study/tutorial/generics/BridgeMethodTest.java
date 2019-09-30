package com.pingfangx.study.tutorial.generics;

import org.junit.Test;

/**
 * 桥接方法，由于 T 被擦为 Object，不能正常赋盖，所以需要桥接
 *
 * @author pingfangx
 * @date 2019/9/29
 */
public class BridgeMethodTest {
    class SupperType<T> {
        public T update(T t) {
            return t;
        }
    }

    class SubType extends SupperType<Integer> {
        @Override
        public Integer update(Integer integer) {
            return super.update(integer);
        }
    }

    @Test
    public void test() {
        SupperType<Integer> supperType = new SubType();
        //方法签名是 Object
        supperType.update(1);
        SubType subType = new SubType();
        //方法签名是 Integer
        subType.update(2);
    }
}
