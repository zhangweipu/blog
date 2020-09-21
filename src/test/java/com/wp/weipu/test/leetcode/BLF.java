package com.wp.weipu.test.leetcode;

import javax.sound.midi.Soundbank;
import java.util.*;

public class BLF {

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[][] edges=new int[n][2];
        for(int i=0;i<n;i++){
            edges[i][0]=in.nextInt();
            edges[i][1]=in.nextInt();
        }
        String ans=removeOneConnection(edges,n);
        System.out.println(ans);
    }
    static List<List<Integer>> lists;
    static int[] visited;
    static boolean valid = true;

    public static String removeOneConnection(int[][] edges, int n) {
        lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lists.add(new ArrayList<>());
        }
        visited = new int[n];
        for (int[] edg : edges) {
            lists.get(edg[1]-1).add(edg[0]-1);
        }
        for (int i = 0; i < n && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static void dfs(int i) {
        visited[i] = 1;
        for (int v : lists.get(i)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[i] == 1) {
                valid = false;
                return;
            }
            visited[i] = 2;
        }
    }
}
