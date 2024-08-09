package com.mirror.a5G网络建设;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //节点个数
        int n = scanner.nextInt();
        //节点关系，或者说节点之间的权重信息
        //或者说叫节点的边信息
        int m = scanner.nextInt();
        //存储边信息和权重
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int p = scanner.nextInt();
            //如果无关系，就正常设置权重
            if (p == 0) {
                graph[x][y] = z;
                graph[y][x] = z;
            } else {
                graph[x][y] = 0;
                graph[y][x] = 0;
            }
        }

        System.out.println(prim(graph, n));
    }

    private static int prim(int[][] graph, int n) {
        //输出的结果
        int minWeight = 0;
        //是否在最小生成树中
        boolean[] inTree = new boolean[n + 1];
        //第一个节点是放到生成树中
        inTree[1] = true;
        //最小生成树节点数量
        int inTree_count = 1;
        //代表， 节点 i到最小生成树集合的最短距离
        int[] dis = new int[n + 1];
        //这是第一次初始化，先把1节点的放进去
        for (int i = 1; i <= n; i++) {
            dis[i] = graph[1][i];
        }
        //如果最小生成树中点的数据量达到n个，就结束循环
        while (inTree_count < n) {
            //记录其他节点到这最近的举例
            int minDis = Integer.MAX_VALUE;
            //这是记录节点
            int nodeIdx = 0;

            for (int i = 1; i <= n; i++) {
                //这是寻找最小节点距离放入
                if (!inTree[i] && dis[i] < minDis) {
                    minDis = dis[i];
                    nodeIdx = i;
                }
            }
            //这个结果表明有节点无法到入这个地方
            if (nodeIdx == 0) {
                return -1;
            }
            //然后处理这个节点，进行更新状态
            inTree[nodeIdx] = true;
            inTree_count++;
            minWeight += dis[nodeIdx];
            //同时也需要更新dis数组，更新条件很简单，只要节点距离更小即可
            for (int i = 1; i <= n; i++) {
                if (!inTree[i] && graph[nodeIdx][i] < dis[i]) {
                    dis[i] = graph[nodeIdx][i];
                }
            }
        }
        return minWeight;
    }
}
