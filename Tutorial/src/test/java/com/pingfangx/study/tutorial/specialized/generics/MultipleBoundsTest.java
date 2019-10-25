package com.pingfangx.study.tutorial.specialized.generics;

/**
 * @author pingfangx
 * @date 2019/9/30
 */
public class MultipleBoundsTest {
    class AClass {
        void classMethod() {
        }
    }

    class BClass {
    }

    interface AInterface {
        void interfaceMethod();
    }

    interface BInterface {
    }

    interface CInterface {
    }

    interface DInterface {
    }


    class MultipleInterfaceTest<T extends AInterface & BInterface & CInterface & DInterface> {
        private T field;
    }

    class MultipleInterfaceTest1<T extends DInterface & CInterface & BInterface & AInterface> {
        private T field;
    }

    //出错，类必须在开头，但只有一个类可以在开头
//    class MultipleClassTest<T extends AClass & BClass> {
//    }
    class MultipleClassTest<T extends AClass & AInterface> {
        private T field;

        public void test(T t) {
            t.classMethod();
            t.interfaceMethod();
        }
    }
}
