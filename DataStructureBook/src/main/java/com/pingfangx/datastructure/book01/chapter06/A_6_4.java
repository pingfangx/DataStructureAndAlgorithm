package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.common.util.IOUtils;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/12/5
 */
public class A_6_4 {
    public static void main(String[] args) {
        LogUtils.d(createBiTree());
    }

    //创建二叉树
    public static BiTree createBiTree() {
        int data = IOUtils.getInt("输入");
        if (data <= 0) {
            return null;
        }
        BiTree biTree = new BiTree();
        biTree.data = data;
        LogUtils.d("创建 %d 的左树", data);
        biTree.lchild = createBiTree();
        LogUtils.d("创建 %d 的右树", data);
        biTree.rchild = createBiTree();
        return biTree;
    }
}
