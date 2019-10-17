package com.pingfangx.study.book1.chapter15.selfbounding;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class SelfBoundingAndCovariantArguments {

    class OrdinarySetter<T extends OrdinarySetter<T>> {
        void set(T t) {
            System.out.println("OrdinarySetter.set)");
        }
    }

    class DerivedSetter extends OrdinarySetter<DerivedSetter> {
//        @Override
//        void set(OrdinarySetter ordinarySetter) {
//            super.set(ordinarySetter);
//        }

        @Override
        void set(DerivedSetter derivedSetter) {
            super.set(derivedSetter);
            System.out.println("DerivedSetter.set)");
        }
    }

    @Test
    public void test() {
        OrdinarySetter ordinarySetter = new DerivedSetter();
        DerivedSetter derivedSetter = new DerivedSetter();
        ordinarySetter.set(ordinarySetter);
        ordinarySetter.set(derivedSetter);

        //错误，已被覆盖
        //derivedSetter.set(ordinarySetter);
        derivedSetter.set(derivedSetter);
    }
}
