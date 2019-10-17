package com.pingfangx.study.book1.chapter15;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class WildcardTest {

    class Wildcards {
    }

    /**
     * 原生类型
     */
    void rawArgs(Holder holder, Object arg) {
        //不知道 holder 的具体类型
        // Unchecked call to 'set(T)' as a member of raw type Holder
        holder.set(arg);
        holder.set(new Wildcards());

        // 没有 T
        //T t = holder.get();
        // 类型信息丢失
        Object obj = holder.get();
    }

    /**
     * 无界通配符
     * 与原生类型似，但不是警告而是错误
     */
    void unboundedArg(Holder<?> holder, Object arg) {
        //不知道 holder 的具体类型
        // set (capture<?>) in Holder cannot be applied to (java.lang.Object)
        //holder.set(arg);
        //holder.set(new Wildcards());

        // 没有 T
        //T t = holder.get();
        // 类型信息丢失
        Object obj = holder.get();
    }


    <T> T exact1(Holder<T> holder) {
        T t = holder.get();
        return t;
    }

    /**
     * 由于有额外的参数，它们具有不同的限制
     * 例如当传原生类型时，exact1 只能返回 Object，而 exact2 可以根据 arg 判断具体类型
     */
    <T> T exact2(Holder<T> holder, T arg) {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    <T> T wildSubType(Holder<? extends T> holder, T arg) {
        //只知道是 T 的子类，不知道具体的类型，不能 set
        //'set(capture<? extends T>)' in 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder' cannot be applied to '(T)'
        //holder.set(arg);
        T t = holder.get();
        return t;
    }

    <T> void wildSuperType(Holder<? super T> holder, T arg) {
        //虽然不知道具体类型，但 T 是其只类，可以 set
        holder.set(arg);
        //是 T 的父类型，不一定是 T
        //Incompatible types. Found: 'capture<? super T>', required: 'T'
        //T t = holder.get();
        //丢失类型信息
        Object obj = holder.get();
    }

    @Test
    public void test() {
        Holder raw = new Holder<Long>();
        raw = new Holder();
        Holder<Long> qualified = new Holder<>();
        Holder<?> unbounded = new Holder<>();
        unbounded = new Holder<Long>();
        Holder<? extends Long> upperBounded = new Holder<>();
        upperBounded = new Holder<Long>();
        Holder<? super Long> lowerBounded = new Holder<>();
        lowerBounded = new Holder<Long>();

        rawArgs(raw, 1L);
        rawArgs(qualified, 1L);
        rawArgs(unbounded, 1L);
        rawArgs(upperBounded, 1L);
        rawArgs(lowerBounded, 1L);

        unboundedArg(raw, 1L);
        unboundedArg(qualified, 1L);
        unboundedArg(unbounded, 1L);
        unboundedArg(upperBounded, 1L);
        unboundedArg(lowerBounded, 1L);

        //Unchecked assignment: 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder' to 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder<java.lang.Object>'
        Object r1 = exact1(raw);
        Long r2 = exact1(qualified);
        //无界只能返回 Object
        Object r3 = exact1(unbounded);
        //上界可以返回
        Long r4 = exact1(upperBounded);
        //下界只能返回 Object
        Object r41 = exact1(lowerBounded);

        //Unchecked assignment: 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder' to 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder<java.lang.Integer>'
        Long r5 = exact2(raw, 1L);
        Long r6 = exact2(qualified, 1L);
        //无界不知道类型
        //'exact2(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<T>, T)' in 'com.pingfangx.study.book1.chapter15.WildcardTest' cannot be applied to '(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<capture<?>>, long)'
        //Long r7 = exact2(unbounded, 1L);
        //上界也不知道具体类型
        //'exact2(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<T>, T)' in 'com.pingfangx.study.book1.chapter15.WildcardTest' cannot be applied to '(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<capture<? extends java.lang.Long>>, long)'
        //Long r8 = exact2(upperBounded, 1L);
        //下界肯定是 Object
        Object r81 = exact2(lowerBounded, 1L);

        //Unchecked assignment: 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder' to 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder<? extends java.lang.Long>'
        Long r9 = wildSubType(raw, 1L);
        Long r10 = wildSubType(qualified, 1L);
        Object r11 = wildSubType(unbounded, 1L);
        Long r12 = wildSubType(upperBounded, 1L);
        Object r121 = wildSubType(lowerBounded, 1L);

        //Unchecked assignment: 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder' to 'com.pingfangx.study.book1.chapter15.WildcardTest.Holder<? super java.lang.Long>'
        wildSuperType(raw, 1L);
        wildSuperType(qualified, 1L);
        //'lowerBoundedWildcard(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<? super T>, T)' in 'com.pingfangx.study.book1.chapter15.WildcardTest' cannot be applied to '(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<capture<?>>, long)'
        //lowerBoundedWildcard(unbounded,1L);
        //'lowerBoundedWildcard(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<? super T>, T)' in 'com.pingfangx.study.book1.chapter15.WildcardTest' cannot be applied to '(com.pingfangx.study.book1.chapter15.WildcardTest.Holder<capture<? extends java.lang.Long>>, long)'
        //lowerBoundedWildcard(upperBounded,1L);
        wildSuperType(lowerBounded, 1L);
    }
}
