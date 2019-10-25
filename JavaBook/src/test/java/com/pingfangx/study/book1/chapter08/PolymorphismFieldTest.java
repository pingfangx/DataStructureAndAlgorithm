package com.pingfangx.study.book1.chapter08;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class PolymorphismFieldTest {
    class Super {
        public String field = "superField";

        public String getField() {
            return field;
        }
    }

    class Sub extends Super {
        public String field = "subField";

        @Override
        public String getField() {
            return field;
        }

        public String getSuperField() {
            return super.field;
        }
    }

    /*
    sup.field=superField,sup.getField()=subField
    sub.field=subField,sub.getField()=subField,sub.getSuperField()=superField
     */
    @Test
    public void test() {
        Super sup = new Sub();
        //因为类型为 Super，所以 sup.field 引用的父类的字段
        System.out.printf("sup.field=%s,sup.getField()=%s%n", sup.field, sup.getField());
        Sub sub = new Sub();
        System.out.printf("sub.field=%s,sub.getField()=%s,sub.getSuperField()=%s", sub.field, sub.getField(), sub.getSuperField());
    }
}
