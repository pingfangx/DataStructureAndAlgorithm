package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2017/12/27
 */
public class A_7_15 {
    /**
     * 7.15
     * <p>
     * 原理：
     * D[j]=Min{D[i]|v(i)∈V}
     * 是从 v 出发的所有最短路径中的最短的一条，路径为 (v,v(i))
     * 有了最短的这一条，可以求出第二短的一条
     * D[j]=Min{D[i]|v(i)∈V-S}
     * D[i] 是弧 (v,v(i)) 上的权，或者是 D[k](v(k)∈S) 加上弧 (v(k),v(j)) 上的权
     * 然后循环一遍，就求出了所有的最短路径
     * <p>
     * 算法：
     * 选择最短的一条路径，D[j]=Min{D[i]}，其中 Vi 属于 V-S
     * 将 j 并入 S
     * 如果 D[j]+ arcs[j][k]<D[k] ，说明更短，则修改 D[k]
     * 循环完成后，就求得了到所有顶点的最短距离，求的过程是按到各顶点的最短路径的长度排序的
     * 也就是说，第一个求出的点是在最短路径最小的点，最后求出的最短路径最大
     *
     * @param g  图
     * @param v0 起点
     * @param p  若p[v][w] 为 true 表示 w 是从 v0 到 v 的当前求得最短路径上的点
     *           仅有点不能表示路径顺序，我在算法中双加了 pathList 记录
     * @param d  到达 Vi 的最短路径的长度
     */
    public static void shortestPath_DIJ(MGraph g, int v0, boolean[][] p, int[] d) {
        List<List<Integer>> pathList = new ArrayList<>();
        //表示是否在 S 集中
        boolean[] final_array = new boolean[g.vexnum];
        for (int v = 0; v < g.vexnum; v++) {
            final_array[v] = false;
            MGraph.ArcCell arcCell = g.arcs[v0][v];
            d[v] = arcCell == null ? 0 : arcCell.adj.value;
            for (int w = 0; w < g.vexnum; w++) {
                p[v][w] = false;
            }
            List<Integer> path = new ArrayList<>();
            if (d[v] != 0) {
                //有 v0-v 的弧
                p[v][v0] = true;
                p[v][v] = true;
                path.add(v);
            }
            pathList.add(path);
        }

        d[v0] = 0;
        final_array[v0] = true;
        for (int i = 1; i < g.vexnum; i++) {
            LogUtils.d("i=" + i);
            // 从 V-S 中找出最小的一个来
            int min = Integer.MAX_VALUE;
            int v = -1;
            for (int w = 0; w < g.vexnum; w++) {
                if (!final_array[w]) {
                    if (d[w] != 0 && d[w] < min) {
                        v = w;
                        min = d[w];
                    }
                }
            }
            if (v == -1) {
                continue;
            }
            LogUtils.d("找到最小 v=%d,min=%d", v, min);
            final_array[v] = true;
            //找出后，到 v 最短，则更新最短路径的计算
            //如果 min+弧 vw 更小，则更新 d[w] 为更小的值
            for (int w = 0; w < g.vexnum; w++) {
                MGraph.ArcCell arcCell = g.arcs[v][w];
                if (arcCell == null) {
                    continue;
                }
                if (!final_array[w] && (d[w] == 0 || min + arcCell.adj.value < d[w])) {
                    d[w] = min + arcCell.adj.value;
                    System.arraycopy(p[v], 0, p[w], 0, p[v].length);
                    p[w][w] = true;
                    LogUtils.d("更新路径 w=" + w + "," + d[w]);
                    List<Integer> path = pathList.get(w);
                    path.clear();
                    path.addAll(pathList.get(v));
                    path.add(w);
                }
            }
        }
        LogUtils.d("求出结果");
        for (int i = 0; i < pathList.size(); i++) {
            List<Integer> path = pathList.get(i);
            LogUtils.d("到达" + i + ":" + path.toString());
        }
    }

}
