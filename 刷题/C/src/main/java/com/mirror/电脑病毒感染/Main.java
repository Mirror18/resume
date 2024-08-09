package com.mirror.电脑病毒感染;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        //创建邻接表，用于存储图
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
        int m =scanner.nextInt();
        //节点，后续节点，权值
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph.putIfAbsent(u,new ArrayList<>());
            graph.get(u).add(new int[]{v,w});
        }
        //记录源点到其他剩余的最短耗时
        int[] dist = new int[n+1];

        Arrays.fill(dist,Integer.MAX_VALUE);
        //自身到自身为0
        int src = scanner.nextInt();
        dist[src] = 0;
        //优先队列，已经探索过的路径的重点
        PriorityQueue<Integer> needCheck = new PriorityQueue<>((a,b) -> dist[a] - dist[b]);

        needCheck.add(src);
        //记录对应点是是否在这其中
        boolean[] visited = new boolean[n+1];
        visited[src] = true;

        while(needCheck.size() > 0){
            //取出最优路径的重点作为新的起点
            int cur = needCheck.poll();
            visited[cur] = false;
            //如果 cur有可达到的其他点
            if(graph.containsKey(cur)){
                for (int[] next : graph.get(cur)) {
                    //后续节点
                    int v = next[0];
                    //权值
                    int w = next[1];
                    //新点的权值
                    int newDist = dist[cur] + w;

                    if(dist[v] > newDist){
                        dist[v] = newDist;
                        if(!visited[v]){
                            visited[v] = true;
                            needCheck.add(v);
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++){
            ans = Math.max(ans,dist[i]);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1: ans);
    }
}
