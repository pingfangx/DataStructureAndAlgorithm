package com.pingfangx.study.book1.chapter15.selfbounding;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class OrdinaryArguments {
    class Base {
    }

    class Derived extends Base {
    }

    class OrdinarySetter {
        void set(Base base) {
            System.out.println("OrdinarySetter.set(Base)");
        }
    }

    class DerivedSetter extends OrdinarySetter {
        @Override
        void set(Base base) {
            super.set(base);
        }

        void set(Derived derived) {
            System.out.println("DerivedSetter.set(Derived)");
        }
    }

    @Test
    public void test() {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter derivedSetter = new DerivedSetter();
        //方法重载
        derivedSetter.set(base);
        derivedSetter.set(derived);
    }
}
