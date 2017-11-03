package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.structure.List;
import com.pingfangx.datastructure.common.structure.StaticLinkListNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * 原例中是使用的静态链表
 * 求 (A-B)∪(B-A)
 * A B C
 * B C D
 * ==A∪B-A∩B？
 *
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_14_to_2_17 {

    private static final int MAX_SIZE = 100;

    public static void main(String[] args) {
        StaticLinkListNode[] linkList = new StaticLinkListNode[MAX_SIZE];
        difference(linkList);
        LogUtils.d(linkList);
    }

    static void difference(StaticLinkListNode[] space) {
        initSpace(space);
        int sizeA = 5;
        int sizeB = 5;
        List listA = DataBuilder.size(sizeA).min(1).max(6).unique().print().sort().buildList();
        List listB = DataBuilder.size(sizeB).min(1).max(10).unique().print().sort().buildList();
        int start = malloc(space);
        //end 指元素的最后一个
        int end = start;
        for (int i = 0; i < sizeA; i++) {
            int free = malloc(space);
            space[free].data = listA.get(i);
            space[end].cur = free;
            end = free;
        }
        space[end].cur = 0;
        for (int j = 0; j < sizeB; j++) {
            int element = listB.get(j);
            int pre = start;
            int next = space[start].cur;
            while (next != space[end].cur && (int) space[next].data != element) {
                pre = next;
                next = space[next].cur;
            }
            if (next == space[end].cur) {
                //到结尾
                int free = malloc(space);
                space[free].data = element;
                space[free].cur = space[end].cur;
                space[end].cur = free;
            } else {
                //有元素
                space[pre].cur = space[next].cur;
                free(space, next);
                if (next == end) {
                    //表示最后一个元素
                    end = pre;
                }
            }
        }
        //输出
        int i=space[start].cur;
        while (i!=0){
            LogUtils.d(space[i].data);
            i=space[i].cur;
        }
    }

    /**
     * 2.14
     */
    static void initSpace(StaticLinkListNode[] space) {
        for (int i = 0; i < MAX_SIZE - 1; i++) {
            space[i] = new StaticLinkListNode(null, i + 1);
        }
        space[MAX_SIZE - 1] = new StaticLinkListNode(null, 0);
    }

    /**
     * 2.15
     */
    static int malloc(StaticLinkListNode[] space) {
        int i = space[0].cur;
        if (space[0].cur != 0) {
            //将空闲位置返回，指向下一个空闲位置
            space[0].cur = space[i].cur;
        }
        return i;
    }

    /**
     * 2.16，回收 k 位置
     */
    static void free(StaticLinkListNode[] space, int k) {
        space[k].cur = space[0].cur;
        space[0].cur = k;
    }


}
