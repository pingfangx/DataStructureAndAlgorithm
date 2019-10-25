package com.pingfangx.study.tutorial.specialized.reflect._class;

/**
 * @author pingfangx
 * @date 2019/9/29
 */
public class MemberTestClass {
    public class ParentClass {
        public int publicParentField;
        int packagePrivateParentField;
        protected int protectedParentField;
        protected int privateParentField;
    }

    public class ChildClass extends ParentClass {
        public int publicChildField;
        int packagePrivateChildField;
        protected int protectedChildField;
        protected int privateChildField;
    }
}
