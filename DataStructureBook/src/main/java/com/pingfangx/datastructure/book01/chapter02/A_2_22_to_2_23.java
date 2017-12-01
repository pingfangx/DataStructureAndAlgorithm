package com.pingfangx.datastructure.book01.chapter02;

import com.pingfangx.datastructure.common.structure.LinkList;
import com.pingfangx.datastructure.common.structure.LinkNode;
import com.pingfangx.datastructure.common.util.DataBuilder;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_2_22_to_2_23 {
    public static void main(String[] args) {
        LinkList listA = DataBuilder.size(5).max(10).sort().print().unique().buildLinkList();
        LinkList listB = DataBuilder.size(5).max(10).sort().print().unique().buildLinkList();
        LogUtils.d(addPolyn(listA, listB));
    }

    /**
     * 简单地用 index 表示系数，data 表示指数
     */
    private static Object addPolyn(LinkList listA, LinkList listB) {
        StringBuilder stringBuilder = new StringBuilder();
        LinkNode nodeA = listA.next;
        LinkNode nodeB = listB.next;
        LinkNode nodeC = null;
        int a = 0;
        int b = 0;
        while (nodeA != null && nodeB != null) {
            LinkNode node = null;
            if ((int) nodeA.data < (int) nodeB.data) {
                a++;
                stringBuilder.append('+');
                stringBuilder.append(a);
                stringBuilder.append('^');
                stringBuilder.append(nodeA.data);
                nodeA = nodeA.next;
            } else if ((int) nodeA.data > (int) nodeB.data) {
                b++;
                stringBuilder.append('+');
                stringBuilder.append(b);
                stringBuilder.append('^');
                stringBuilder.append(nodeB.data);
                nodeB = nodeB.next;
            } else {
                a++;
                b++;
                stringBuilder.append('+');
                //指数相加
                stringBuilder.append((a + b));
                stringBuilder.append('^');
                //系数不变
                stringBuilder.append(nodeA.data);
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }

        while (nodeA != null) {
            a++;
            stringBuilder.append('+');
            stringBuilder.append(a);
            stringBuilder.append('^');
            stringBuilder.append(nodeA.data);
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            b++;
            stringBuilder.append('+');
            stringBuilder.append(b);
            stringBuilder.append('^');
            stringBuilder.append(nodeB.data);
            nodeB = nodeB.next;
        }
        stringBuilder.deleteCharAt(0);
        return stringBuilder.toString();
    }
}
