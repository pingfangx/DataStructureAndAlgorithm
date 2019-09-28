package com.pingfangx.study.tutorial.reflect.constructor;

/**
 * @author pingfangx
 * @date 2019/5/31
 */
public class SyntheticConstructorTest {
    /**
     * 由于内部类的构造函数引用了封闭类的私有构造函数，因此编译器必须生成包私有构造函数。
     */
    public class SyntheticConstructor {
        private SyntheticConstructor() {
        }

        class Inner {
            Inner() {
                new SyntheticConstructor();
            }
        }
    }
}
