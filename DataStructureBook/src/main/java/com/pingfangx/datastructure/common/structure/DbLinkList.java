package com.pingfangx.datastructure.common.structure;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class DbLinkList {
    public DbLinkNode next;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        DbLinkNode next = this.next;
        DbLinkNode pre = null;
        while (next != null) {
            stringBuilder.append(next.data);
            pre = next;
            next = next.next;
            if (next != null) {
                stringBuilder.append('-');
                stringBuilder.append('>');
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        while (pre != null) {
            stringBuilder2.insert(0, pre.data);
            pre = pre.prior;
            if (pre != null) {
                stringBuilder2.insert(0, '-');
                stringBuilder2.insert(0, '<');
            }
        }
        stringBuilder.append('\n');
        stringBuilder.append(stringBuilder2);
        return stringBuilder.toString();
    }
}
