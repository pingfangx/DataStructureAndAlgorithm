package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Stack;

/**
 * @author pingfangx
 * @date 2017/12/26
 */
public class A_7_13_to_7_14 {
    int[] ve = new int[Graph.MAX_VERTEX_NUM];

    /**
     * 7.13
     */
    public STATUS topologicalSort(ALGraph g, Stack<Integer> t) {
        int[] indegree = A_7_12.findInDegree(g);
        Stack<Integer> s = new Stack<>();
        //为 0 的进栈
        for (int i = 0; i < g.vexnum; i++) {
            if (indegree[i] == 0) {
                s.push(i);
            }
        }
        int count = 0;
        ve = new int[g.vexnum];
        while (!s.empty()) {
            int j = s.pop();
            t.push(j);
            LogUtils.d("出栈" + j + "," + g.vertices[j].data);
            count++;
            for (ALGraph.ArcNode p = g.vertices[j].firstarc; p != null; p = p.nextarc) {
                int k = p.adjvex;
                //如果入度为 0 则入栈
                if (--indegree[k] == 0) {
                    s.push(k);
                }
                if (ve[j] + p.info.value > ve[k]) {
                    ve[k] = ve[j] + p.info.value;
                }
            }
        }
        if (count < g.vexnum) {
            return STATUS.ERROR;
        } else {
            return STATUS.OK;
        }
    }

    STATUS criticalPath(ALGraph g) {
        Stack<Integer> t = new Stack<>();
        if (topologicalSort(g, t) != STATUS.OK) {
            return STATUS.ERROR;
        }
        int[] vl = new int[ve.length];
        System.arraycopy(ve, 0, vl, 0, ve.length);
        while (!t.empty()) {
            int j = t.pop();
            for (ALGraph.ArcNode p = g.vertices[j].firstarc; p != null; p = p.nextarc) {
                int k = p.adjvex;
                int dut = p.info.value;
                if (vl[k] - dut < vl[j]) {
                    vl[j] = vl[k] - dut;
                }
            }
        }
        for (int j = 0; j < g.vexnum; j++) {
            //原文这里有错误，多了一个分号
            for (ALGraph.ArcNode p = g.vertices[j].firstarc; p != null; p = p.nextarc) {
                int k = p.adjvex;
                int dut = p.info.value;
                int ee = ve[j];
                int el = vl[k] - dut;
                String tag = ee == el ? "*" : "";
                LogUtils.d("%d,%d,%d,%d,%d,%s", j, k, dut, ee, el, tag);
            }
        }
        return STATUS.OK;
    }
}
