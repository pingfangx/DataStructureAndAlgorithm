package com.pingfangx.study.tutorial.specialized.reflect.field;

/**
 * @author pingfangx
 * @date 2019/5/30
 */
public class FieldThisTest {
    /**
     * 可以看到确实与描述一致，$this$0 是用于 最外层的封闭类，内部的依次为 $this$1 $this$2
     */
    public class A {
        public class B {
            public class C {
            }
        }
    }

    public class D {
        // 不一样，生成的是 this$0
        private int $this$0 = 1;
    }
}
