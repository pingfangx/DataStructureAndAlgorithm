package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.book01.common.STATUS;
import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Stack;

/**
 * @author pingfangx
 * @date 2017/12/26
 */
public class A_7_12 {
    public static STATUS topologicalSort(ALGraph g) {
        int[] indegree = findInDegree(g);
        Stack<Integer> s = new Stack<>();
        //为 0 的进栈
        for (int i = 0; i < g.vexnum; i++) {
            if (indegree[i] == 0) {
                s.push(i);
            }
        }
        int count = 0;
        while (!s.empty()) {
            int i = s.pop();
            LogUtils.d("出栈" + i + "," + g.vertices[i].data);
            count++;
            for (ALGraph.ArcNode p = g.vertices[i].firstarc; p != null; p = p.nextarc) {
                int k = p.adjvex;
                //如果入度为 0 则入栈
                if (--indegree[k] == 0) {
                    s.push(k);
                }
            }
        }
        if (count < g.vexnum) {
            return STATUS.ERROR;
        } else {
            return STATUS.OK;
        }
    }

    /**
     * 求入度
     */
    public static int[] findInDegree(ALGraph g) {
        int[] inDegree = new int[g.vexnum];
        for (ALGraph.VNode vertex : g.vertices) {
            if (vertex == null || vertex.firstarc == null) {
                continue;
            }
            ALGraph.ArcNode arc = vertex.firstarc;
            while (arc != null) {
                //入度 ，++
                inDegree[arc.adjvex]++;
                arc = arc.nextarc;
            }
        }
        return inDegree;
    }
}
