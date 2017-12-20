package com.pingfangx.datastructure.book01.common;

import com.pingfangx.datastructure.book01.chapter02.*;
import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Random;

/**
 * @author pingfangx
 * @date 2017/11/3
 */
public class DataBuilder {
    /**
     * 大小
     */
    private int size = 10;
    /**
     * 最小值，默认为0
     */
    private int min = 0;
    /**
     * 最大值，默认为100
     */
    private int max = 100;
    /**
     * 是否排序
     */
    private boolean sorted;
    /**
     * 元素是否唯一
     */
    private boolean unique;
    /**
     * 生成后是否打印
     */
    private boolean print;

    public List buildList() {
        List list = buildListInner();
        if (print) {
            LogUtils.d(list);
        }
        return list;
    }

    private List buildListInner() {
        List list = new List();
        Random random = new Random();
        if (unique) {
            if (max - min < size) {
                throw new IllegalArgumentException(String.format("size %d is too big to generate list in range [%d,%d)", size, min, max));
            }
            if (max - min > 10000) {
                throw new IllegalArgumentException("range too large");
            }
            List randomList = new List();
            int randomLength = max - min;
            for (int i = 0; i < randomLength; i++) {
                randomList.add(i, i + min);
            }
            for (int i = 0; i < size; i++) {
                int nextIndex = random.nextInt(randomList.length());
                Integer nextInt = randomList.get(nextIndex);
                randomList.remove(nextIndex);
                list.add(i, nextInt);
            }
        } else {
            for (int i = 0; i < size; i++) {
                list.add(i, random.nextInt(max - min) + min);
            }
        }
        if (sorted) {
            sortInner(list);
        }
        return list;
    }

    public LinkList buildLinkList() {
        List list = buildListInner();
        LinkList linkList = new LinkList();
        int length = list.length();
        LinkNode nextNode = new LinkNode(list.get(length - 1), null);
        for (int i = length - 2; i >= 0; i--) {
            nextNode = new LinkNode(list.get(i), nextNode);
        }
        linkList.next = nextNode;
        if (print) {
            LogUtils.d(linkList);
        }
        return linkList;
    }

    public DbLinkList buildDoubleLinkList() {
        List list = buildListInner();
        DbLinkList dbLinkList = new DbLinkList();
        int length = list.length();
        DbLinkNode nextNode = new DbLinkNode(list.get(length - 1), null);
        for (int i = length - 2; i >= 0; i--) {
            nextNode = new DbLinkNode(list.get(i), nextNode);
        }
        dbLinkList.next = nextNode;
        while (nextNode.next != null) {
            nextNode.next.prior = nextNode;
            nextNode = nextNode.next;
        }
        if (print) {
            LogUtils.d(dbLinkList);
        }
        return dbLinkList;
    }

    public StaticLinkListNode[] buildStaticLinkList() {
        List list = buildListInner();
        StaticLinkListNode[] nodes = new StaticLinkListNode[100];
        nodes[0] = new StaticLinkListNode(null, 1);
        int length = list.length();
        for (int i = 0; i < length; i++) {
            nodes[i + 1] = new StaticLinkListNode(list.get(i), i == length - 1 ? 0 : i + 2);
        }
        if (print) {
            LogUtils.d(nodes);
        }
        return nodes;
    }

    private void sortInner(List list) {
        int length = list.length();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int t = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, t);
                }
            }
        }
    }

    private DataBuilder setSizeInner(int size) {
        this.size = size;
        return this;
    }


    public static DataBuilder size(int size) {
        return new DataBuilder().setSizeInner(size);
    }

    public DataBuilder min(int min) {
        this.min = min;
        return this;
    }

    public DataBuilder max(int max) {
        this.max = max;
        return this;
    }

    public DataBuilder sort() {
        this.sorted = true;
        return this;
    }

    public DataBuilder unique() {
        this.unique = true;
        return this;
    }


    public DataBuilder print() {
        this.print = true;
        return this;
    }

    public static Object[] buildSString(String string) {
        Object[] result = new Object[string.length() + 1];
        result[0] = string.length();
        for (int i = 0; i < string.length(); i++) {
            result[i + 1] = string.charAt(i);
        }
        return result;
    }
}
