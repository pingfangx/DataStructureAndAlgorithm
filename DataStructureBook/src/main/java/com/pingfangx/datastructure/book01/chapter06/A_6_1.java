package com.pingfangx.datastructure.book01.chapter06;

import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/12/5
 */
public class A_6_1 {

    public static STATUS visit(int data) {
        LogUtils.d(data);
        return STATUS.OK;
    }

    public static STATUS preorderTraverse(BiTree biTree) {
        if (biTree != null) {
            if (visit(biTree.data) == STATUS.OK) {
                if (preorderTraverse(biTree.lchild) == STATUS.OK) {
                    if (preorderTraverse(biTree.rchild) == STATUS.OK) {
                        return STATUS.OK;
                    }
                }
            }
            //都成功则返回 ok ，否则返回 error
            return STATUS.ERROR;
        } else {
            //为 null 也返回 ok
            return STATUS.OK;
        }
    }

    public static STATUS inorderTraverse(BiTree biTree) {
        if (biTree != null) {
            if (inorderTraverse(biTree.lchild) == STATUS.OK) {
                if (visit(biTree.data) == STATUS.OK) {
                    if (inorderTraverse(biTree.rchild) == STATUS.OK) {
                        return STATUS.OK;
                    }
                }
            }
            //都成功则返回 ok ，否则返回 error
            return STATUS.ERROR;
        } else {
            //为 null 也返回 ok
            return STATUS.OK;
        }
    }

    public static STATUS postorderTraverse(BiTree biTree) {
        if (biTree != null) {
            if (postorderTraverse(biTree.lchild) == STATUS.OK) {
                if (postorderTraverse(biTree.rchild) == STATUS.OK) {
                    if (visit(biTree.data) == STATUS.OK) {
                        return STATUS.OK;
                    }
                }
            }
            //都成功则返回 ok ，否则返回 error
            return STATUS.ERROR;
        } else {
            //为 null 也返回 ok
            return STATUS.OK;
        }
    }
}
