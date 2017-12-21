package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;

import java.util.Stack;

/**
 * @author pingfangx
 * @date 2017/12/5
 */
public class A_6_3 {
    public static STATUS inorderTraverse(BiTree biTree) {
        Stack<BiTree> stack = new Stack<>();
        BiTree p = biTree;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //进栈，访问左子树
                stack.push(p);
                p = p.lchild;
            } else {
                //退栈
                p = stack.pop();
                //访问结点
                if (A_6_1.visit(p.data) != STATUS.OK) {
                    return STATUS.ERROR;
                }
                //访问右子树
                p = p.rchild;
            }
        }
        return STATUS.OK;
    }
}
