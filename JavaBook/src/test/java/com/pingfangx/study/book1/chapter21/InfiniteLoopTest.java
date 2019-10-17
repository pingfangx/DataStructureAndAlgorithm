package com.pingfangx.study.book1.chapter21;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class InfiniteLoopTest {
    /**
     * <pre>
     *      test_while()V
     *    L0
     *     LINENUMBER 9 L0
     *     ICONST_1
     *     ISTORE 1
     *    L1
     *     LINENUMBER 10 L1
     *    FRAME APPEND [I]
     *     ILOAD 1
     *     IFEQ L2
     *     GOTO L1
     *    L2
     *     LINENUMBER 12 L2
     *    FRAME SAME
     *     RETURN
     *    L3
     *     LOCALVARIABLE this Lcom/pingfangx/study/book1/InfiniteLoopTest; L0 L3 0
     *     LOCALVARIABLE b Z L1 L3 1
     *     MAXSTACK = 1
     *     MAXLOCALS = 2
     * </pre>
     */
    void test_while() {
        boolean b = true;
        while (b) {
        }
    }

    /**
     * <pre>
     *       test_whileTrue()V
     *    L0
     *     LINENUMBER 9 L0
     *    FRAME SAME
     *     GOTO L0
     *    L1
     *     LOCALVARIABLE this Lcom/pingfangx/study/book1/InfiniteLoopTest; L0 L1 0
     *     MAXSTACK = 0
     *     MAXLOCALS = 1
     * </pre>
     */
    void test_whileTrue() {
        while (true) {
        }
    }

    /**
     * <pre>
     *       test_for()V
     *    L0
     *     LINENUMBER 14 L0
     *    FRAME SAME
     *     GOTO L0
     *    L1
     *     LOCALVARIABLE this Lcom/pingfangx/study/book1/InfiniteLoopTest; L0 L1 0
     *     MAXSTACK = 0
     *     MAXLOCALS = 1
     * </pre>
     */
    void test_for() {
        for (; ; ) {
        }
    }
}
