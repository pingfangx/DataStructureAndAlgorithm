package com.pingfangx.datastructure.book01.common;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public enum STATUS {
    TRUE(1), FALSE(0), OK(1), ERROR(0), INFEASIBLE(-1), OVERFLOW(-2),;

    private int code;

    STATUS(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
