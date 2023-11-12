package com.wp.weipu.test.forjob.data;

public class Graph {

    int[][] graph;
    int n;
    int[] colors;
    int count;

    public int countColors(int[][] gra) {
        this.graph = gra;
        n = graph.length;
        colors = new int[n];
        count = 0;
        tryColoring(0);
        return count;
    }

    public void tryColoring(int node) {
        if (node == n) {
            count++;
            return;
        }
        //两种颜色
        for (int i=1;i<=2;i++) {
            colors[node] = i;
            if (isValid(node)) {
                tryColoring(node + 1);
            }
        }
        colors[node] =0;
    }

    public boolean isValid(int node) {
        for(int i=1;i<=n;i++) {
            if(graph[node][i] == 1 && colors[node] == colors[i] && colors[node] == 1) {
                return false;
            }
        }
        return true;
    }



}
