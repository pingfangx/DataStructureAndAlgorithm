package com.pingfangx.datastructure.book01.chapter07;

/**
 * todo 没有运行，不太理解，以后再阅读
 *
 * @author pingfangx
 * @date 2017/12/27
 */
public class A_7_16 {
    void shortestPath_FLOYD(MGraph g, boolean[][][] p, int[][] d) {
        for (int v = 0; v < g.vexnum; v++) {
            for (int w = 0; w < g.vexnum; w++) {
                d[w][w] = g.arcs[v][w].adj.value;
                for (int u = 0; u < g.vexnum; u++) {
                    p[v][w][u] = false;
                }
                if (d[v][w] < Integer.MAX_VALUE) {
                    p[v][w][v] = true;
                    p[v][w][w] = true;
                }
            }
        }
        for (int u = 0; u < g.vexnum; u++) {
            for (int v = 0; v < g.vexnum; v++) {
                for (int w = 0; w < g.vexnum; w++) {
                    if (d[v][u] + d[u][w] < d[v][w]) {
                        d[v][w] = d[v][u] + d[u][w];
                        for (int i = 0; i < g.vexnum; i++) {
                            p[v][w][i] = p[v][u][i] || p[u][w][i];
                        }
                    }
                }
            }
        }
    }
}
