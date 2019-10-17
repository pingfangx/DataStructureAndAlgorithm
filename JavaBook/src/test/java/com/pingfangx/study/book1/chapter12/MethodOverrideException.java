package com.pingfangx.study.book1.chapter12;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class MethodOverrideException {
    class Super {
        public void exception() throws Exception {
        }

        public void runtimeException() throws RuntimeException {
        }
    }

    class Sub extends Super {
        //未处理
//        @Override
//        public void exception() {
//            super.exception();
//        }

        //不能缩小
//        @Override
//        public void exception() throws RuntimeException {
//            super.exception();
//        }

        //不能变更，否则作为基类使用无法判断是否捕获取异常
//        @Override
//        public void runtimeException() throws RuntimeException, Exception {
//            super.runtimeException();
//        }
    }
}
