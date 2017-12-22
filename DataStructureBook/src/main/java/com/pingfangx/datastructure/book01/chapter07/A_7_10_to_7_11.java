package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * todo 待理解
 * 主要讲了
 * 关节点
 * 重连通图
 * 连通度
 *
 * @author pingfangx
 * @date 2017/12/25
 */
public class A_7_10_to_7_11 {
    int[] visited = new int[Graph.MAX_VERTEX_NUM];
    int[] low = new int[Graph.MAX_VERTEX_NUM];
    private int count;


    /**
     * 7.10
     */
    void findArticul(ALGraph g) {
        count = 1;
        visited[0] = 1;
        //其余顶点尚未访问
        for (int i = 1; i < g.vexnum; i++) {
            visited[i] = 0;
        }
        ALGraph.ArcNode p = g.vertices[0].firstarc;
        int v = p.adjvex;
        DFSArticul(g, v);
        if (count < g.vexnum) {
            //生成树的根有至少两颗子树
            //根是关节点，输出
            LogUtils.d(0 + g.vertices[0].data.toString());
            while (p.nextarc != null) {
                p = p.nextarc;
                v = p.adjvex;
                if (visited[v] == 0) {
                    DFSArticul(g, v);
                }
            }
        }
    }

    /**
     * 7.11
     * 从第 v 个顶点出发，深度优先遍历图 g ，查找并输出并节点
     */
    private void DFSArticul(ALGraph g, int v) {
        int min = ++count;
        visited[v] = min;

        for (ALGraph.ArcNode p = g.vertices[v].firstarc; p != null; p = p.nextarc) {
            //w 是邻接顶点
            int w = p.adjvex;
            if (visited[w] == 0) {
                //示访问，是 v 的孩子
                DFSArticul(g, w);
                if (low[w] < min) {
                    min = low[w];
                }
                if (low[w] >= visited[v]) {
                    //关节点
                    LogUtils.d(v + g.vertices[v].data.toString());
                }
            } else if (visited[w] < min) {
                min = visited[w];
            }
        }
        low[v] = min;
    }
}
