package com.pingfangx.study.book1.chapter21.section03;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public abstract class IntGenerator {
    private volatile boolean canceled;

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public abstract int next();
}
