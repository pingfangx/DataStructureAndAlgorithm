package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.book01.common.STATUS;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_3 {
    public static STATUS initList() {
        SqList sqList = new SqList();
        if (sqList.elem == null) {
            return STATUS.OVERFLOW;
        }
        sqList.length = 0;
        sqList.size = SqList.LIST_INIT_SIZE;
        return STATUS.OK;
    }
}
