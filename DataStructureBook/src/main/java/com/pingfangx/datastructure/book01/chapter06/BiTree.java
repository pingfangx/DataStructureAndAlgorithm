package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.chapter02.List;

/**
 * 二叉树
 *
 * @author pingfangx
 * @date 2017/12/4
 */
public class BiTree {
    public static final String SEPARATOR = "-";
    public BiTree lchild;
    public BiTree rchild;
    public int data;

    /**
     * 创建指定深度的满二叉树
     *
     * @param depth 深度
     */
    public static BiTree buildByDepth(int depth) {
        return buildByDepth(depth, 1);
    }

    private static BiTree buildByDepth(int depth, int index) {
        if (depth < 1 || index < 1) {
            return null;
        }
        BiTree biTree = new BiTree();
        biTree.data = index;
        if (depth > 1) {
            biTree.lchild = buildByDepth(depth - 1, index * 2);
            biTree.rchild = buildByDepth(depth - 1, index * 2 + 1);
        }
        return biTree;
    }


    /**
     * 创建指定结点数的完全二叉树
     *
     * @param nodeNumber 结点数
     */
    public static BiTree buildByNodeNumber(int nodeNumber) {
        List list = new List();
        //多加了0
        for (int i = 0; i <= nodeNumber; i++) {
            list.add(i, i + 1);
        }
        return buildByList(list);
    }

    public static BiTree buildByArray(int[] data) {
        return buildByList(new List(data));
    }

    /**
     * 创建指定各结点值的二叉树
     * 约定若结点值为负表示没有结点，可用来创建普通二叉树
     */
    public static BiTree buildByList(List data) {
        return buildByList(data, 0);
    }

    private static BiTree buildByList(List data, int index) {
        if (data == null || data.length() == 0 || index < 0 || index >= data.length()) {
            return null;
        }
        if (data.get(index) < 0) {
            return null;
        }
        BiTree biTree = new BiTree();
        biTree.data = data.get(index);
        //因为实际位置是 index+1 ，*2 得到位置，要 索引还需 -1
        biTree.lchild = buildByList(data, (index + 1) * 2 - 1);
        biTree.rchild = buildByList(data, (index + 1) * 2);
        return biTree;
    }

    @Override
    public String toString() {
        String title = format(data);
        String left = lchild == null ? null : lchild.toString();
        String right = rchild == null ? null : rchild.toString();
        return merge(title, left, right);
    }

    /**
     * 向 stringBuilder 添加 n 个空格
     */
    public static void append(StringBuilder stringBuilder, int n) {
        for (int i = 0; i < n; i++) {
            stringBuilder.append(SEPARATOR);
        }
    }


    /**
     * 将 content 填充到 length 长度
     * 使其居中，左右填充
     */
    public static StringBuilder append(String content, int length) {
        //取左边的，可能不能整除，所以右边的又减了一次
        int spaceCount = (length - content.length()) / 2;
        StringBuilder stringBuilder = new StringBuilder();
        //添加左边的空格
        append(stringBuilder, spaceCount);
        //添加内容
        stringBuilder.append(content);
        //添加右边的空格
        append(stringBuilder, length - stringBuilder.length());
        return stringBuilder;
    }

    /**
     * 格式化
     */
    public static String format(int data) {
        return String.format("%03d", data);
    }

    public static String merge(String title, String s1, String s2) {
        if (title == null) {
            title = "";
        }
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        if (s1.equals("") && s2.equals("")) {
            return title;
        }
        String[] a1 = s1.split("\n");
        String[] a2 = s2.split("\n");
        int length1 = a1.length;
        int length2 = a2.length;
        //求出最长的一行
        int maxLength;
        int maxLineLength;
        if (length1 > length2) {
            maxLength = length1;
        } else {
            maxLength = length2;
        }
        //以最大行的结点数计算最大长度
        int nodeNumber = (int) Math.pow(2, maxLength - 1);
        maxLineLength = format(0).length() * nodeNumber + nodeNumber - 1;
        StringBuilder stringBuilder = new StringBuilder();
        //添加标题
        stringBuilder.append(append(title, maxLineLength * 2 + SEPARATOR.length()));
        stringBuilder.append('\n');
        //添加每一行
        for (int i = 0; i < maxLength; i++) {
            if (i < length1) {
                stringBuilder.append(append(a1[i], maxLineLength));
            } else {
                append(stringBuilder, maxLineLength);
            }
            stringBuilder.append(SEPARATOR);
            if (i < length2) {
                stringBuilder.append(append(a2[i], maxLineLength));
            } else {
                append(stringBuilder, maxLineLength);
            }
            if (i < maxLength - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 填充列表，第0个元素留空，其余对应
     *
     * @param result 结果列表
     * @param index  index
     */
    public void fillList(List result, int index) {
        while (index >= result.length()) {
            result.add(result.length(), 0);
        }
        result.set(index, data);
        if (lchild != null) {
            lchild.fillList(result, index * 2);
        }
        if (rchild != null) {
            rchild.fillList(result, index * 2 + 1);
        }
    }
}
