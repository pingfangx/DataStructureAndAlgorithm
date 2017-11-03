package com.pingfangx.datastructure.common.structure;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class LinkList {
    public LinkNode next;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        LinkNode next = this.next;
        while (next != null) {
            stringBuilder.append(next.data);
            next = next.next;
            if (next != null) {
                stringBuilder.append('-');
                stringBuilder.append('>');
            }
        }
        return stringBuilder.toString();
    }
}
