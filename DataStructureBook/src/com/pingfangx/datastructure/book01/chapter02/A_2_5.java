package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.constant.STATUS;
import com.pingfangx.datastructure.common.structure.List;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class A_2_5 {

    public static void main(String[] args) {
        List list = DataBuilder.size(10).max(10).sort().unique().print().buildList();
        LogUtils.d(delete(list, 10));
        LogUtils.d(list);
    }

    /**
     * 删除，往前移，长度减1
     */
    static Object delete(List list, int index) {
        if (index < 0 || index > list.length()) {
            return STATUS.ERROR;
        }
        for (int i = index; i < list.length() - 1; i++) {
            list.set(i, list.get(i + 1));
        }
        list.remove(list.length()-1);
        return STATUS.OK;
    }
}
