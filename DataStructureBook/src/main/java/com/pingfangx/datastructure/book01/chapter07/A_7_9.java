package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;

/**
 * @author pingfangx
 * @date 2017/12/22
 */
public class A_7_9 {
    Closedge[] closedges = new Closedge[Graph.MAX_VERTEX_NUM];

    public class Closedge {
        MGraph.VertexType adjvex;
        /**
         * 权，存储这个点到其他点的最小花费
         */
        MGraph.VRType lowcost;

        public Closedge(MGraph.VertexType adjvex, MGraph.VRType lowcost) {
            this.adjvex = adjvex;
            this.lowcost = lowcost;
        }

        @Override
        public String toString() {
            return adjvex + "," + lowcost;
        }
    }

    /**
     * 普里姆算法
     * 求最小生成树
     * 以一个顶点为起点,加入U,从 V-U 中找最小权的边，将顶点并入U，重复
     *
     * @param g 图
     * @param u 起点
     */
    public void miniSpanTree_PRIM(MGraph g, int u) {
        //closedges[j] 表示第 j+1 个顶点到第 adjvex 顶点的权
        int k = g.locateVex(u);
        for (int j = 0; j < g.vexnum; j++) {
            if (j != k) {
                MGraph.ArcCell arcCell = g.arcs[k][j];
                if (arcCell != null) {
                    //由 顶点 u+1 引出的各边,其权为 arcCell.adj
                    closedges[j] = new Closedge(new MGraph.VertexType(u + 1), arcCell.adj);
                }
            }
        }
        //初始化 U={u}
        //将权置为 0 表示已经处理过,即并入 U
        closedges[k] = new Closedge(new MGraph.VertexType(u + 1), new MGraph.VRType(0));
        //选择剩余的顶点
        for (int i = 1; i < g.vexnum; i++) {
            //求出下一个结点,及第 k 个顶点
            k = minumun(closedges);
            LogUtils.d("%s,%s", closedges[k].adjvex, g.vexs[k]);
            //第 k 个顶点并入 U 集
            closedges[k].lowcost = new MGraph.VRType(0);
            for (int j = 0; j < g.vexnum; j++) {
                //只修改小于的，如果不小于，会保留邻接点及其权
                MGraph.ArcCell arcCell = g.arcs[k][j];
                if (arcCell != null) {
                    if (closedges[j] == null || arcCell.adj.value < closedges[j].lowcost.value) {
                        closedges[j] = new Closedge(new MGraph.VertexType(g.vexs[k].value), new MGraph.VRType(arcCell.adj.value));
                    }
                }
            }
        }
    }

    /**
     * 求最小权的点
     */
    private int minumun(Closedge[] closedges) {
        int min = 0;
        int min_index = 0;
        for (int i = 0; i < closedges.length; i++) {
            Closedge closedge = closedges[i];
            if (closedge != null && closedge.lowcost.value != 0) {
                if (min == 0 || min > closedge.lowcost.value) {
                    min = closedge.lowcost.value;
                    min_index = i;
                }
            }
        }
        return min_index;
    }
}
