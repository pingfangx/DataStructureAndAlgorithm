package com.pingfangx.study.tutorial.reflect;

/**
 * @author pingfangx
 * @date 2019/5/30
 */
public class FieldTest {
    /**
     * 可以看到确实与描述一致，$this$0 是用于 最外层的封闭类，内部的依次为 $this$1 $this$2
     */
    public class A {
        public class B {
            public class C {
            }
        }
    }
}
