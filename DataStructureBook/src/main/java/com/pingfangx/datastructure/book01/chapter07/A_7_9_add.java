package com.pingfangx.datastructure.book01.chapter07;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2017/12/22
 */
public class A_7_9_add {

    /**
     * 连通分量
     */
    private List<List<Integer>> containPoints = new ArrayList<>();

    public void miniSpanTree(MGraph graph) {
        MGraph.ArcCell minArc = findMinArc(graph);
        while (minArc != null) {
            minArc = findMinArc(graph);
        }
    }

    /**
     * 查找最小边
     */
    private MGraph.ArcCell findMinArc(MGraph graph) {
        MGraph.ArcCell minArc = null;
        MGraph.ArcCell[][] arcs = graph.arcs;
        int findA = -1;
        int findB = -1;
        for (int i = 0; i < arcs.length; i++) {
            MGraph.ArcCell[] arcCells = arcs[i];
            //取右上半
            for (int j = i; j < arcCells.length; j++) {
                MGraph.ArcCell arcCell = arcCells[j];
                if (arcCell != null) {
                    if (minArc == null || arcCell.adj.value < minArc.adj.value) {
                        if (isLegalArc(i, j) && isLegalArc(j, i)) {
                            findA = i;
                            findB = j;
                            minArc = arcCell;
                        }
                    }
                }
            }
        }
        if (minArc != null) {
            putPointPair(findA, findB);
            LogUtils.d("%d,%d", findA + 1, findB + 1);
        }
        return minArc;
    }

    /**
     * 存入边
     */
    private void putPointPair(int a, int b) {
        int[] position = locateArc(a, b);
        int indexA = position[0];
        int indexB = position[1];
        if (indexA == -1 && indexB == -1) {
            //为空
            List<Integer> pointPairs = new ArrayList<>();
            pointPairs.add(a);
            pointPairs.add(b);
            containPoints.add(pointPairs);
        } else if (indexA == -1 || indexB == -1) {
            int i = indexA != -1 ? indexA : indexB;
            containPoints.get(i).add(a);
            containPoints.get(i).add(b);
        } else {
            //都不为 -1，合并两条连通分量
            containPoints.get(indexA).add(a);
            containPoints.get(indexA).add(b);
            containPoints.get(indexA).addAll(containPoints.get(indexB));
            containPoints.set(indexB, null);
        }
    }

    /**
     * 是否是合法的边
     */
    private boolean isLegalArc(int a, int b) {
        int[] position = locateArc(a, b);
        int indexA = position[0];
        int indexB = position[1];
        //至少有一边没有
        return indexA == -1 || indexB == -1 || indexA != indexB;
    }

    /**
     * 定位边的位于哪一连通分量上
     */
    private int[] locateArc(int a, int b) {
        int indexA = -1;
        int indexB = -1;
        for (int i = 0; i < containPoints.size(); i++) {
            List<Integer> pointList = containPoints.get(i);
            if (pointList == null) {
                continue;
            }
            if (pointList.contains(a)) {
                indexA = i;
            }
            if (pointList.contains(b)) {
                indexB = i;
            }
        }
        return new int[]{indexA, indexB};
    }
}
