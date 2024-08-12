package com.mirror.快递员的烦恼;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static int n;
    static int[][] dist;
    static int[][] path;

    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
       Math.pow(16,0);
        int m = sc.nextInt();

        // floyd算法需要基于dist和path矩阵求解
        // dist[i][j] 用于记录点 i->j 的最短距离，初始时等价于邻接矩阵
        dist = new int[n + 1][n + 1];
        // path[i][j] 用于记录点 i->j 最短距离情况下需要经过的中转点，初始时默认任意两点间无中转点，即默认path[i][j] = -1
        path = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                // 初始时默认i,j不相连，即i,j之间距离无穷大
                if (i != j) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
                path[i][j] = -1;
            }
        }

        // 由于本题的客户id不是顺序的，因此这里需要将客户id离散化处理
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int id = sc.nextInt();
            int dis = sc.nextInt();

            // 离散化处理
            map.put(id, i);

            // 投递站到客户之间的距离distance
            dist[0][i] = dis;
            dist[i][0] = dis;
        }

        for (int i = 1; i <= m; i++) {
            int id1 = sc.nextInt();
            int id2 = sc.nextInt();
            int dis = sc.nextInt();

            int i1 = map.get(id1);
            int i2 = map.get(id2);

            // 客户与客户之间的距离信息
            dist[i1][i2] = dis;
            dist[i2][i1] = dis;
        }

        // floyd算法调用
        floyd();
        // ans记录经过所有点后回到出发点的最短距离
        ans = Integer.MAX_VALUE;
        // 全排列模拟经过所有点的路径
        dfs(0, 0, new boolean[n + 1], 0);

        System.out.println(ans);
    }

    // floyd算法求解图中任意两点之间的最短路径
    public static void floyd() {
        for (int k = 0; k < n + 1; k++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    // newDist是经过k后，i->j的距离
                    int newDist = dist[i][k] + dist[k][j];
                    // 如果newDist是i->j的更短路径
                    if (newDist < dist[i][j]) {
                        // 则更新i->j的最短距离
                        dist[i][j] = newDist;
                        // 且此更短距离需要经过k, path[i][j]即记录 i->j 最短距离下需要经过点 k
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    /**
     * 找一条经过所有点的最短路径，我们可以求解所有点形成的全排列，每一个全排列都对应一条经过所有点的路径，只是经过点的先后顺序不同 //
     * 求某个全排列过程中，可以通过dist数组，累计上一个点i到下一个点j的最短路径dist[i][j]
     *
     * @param pre 上一个点, 初始为0，表示从快递站出发
     * @param sum 当前全排列路径累计的路径权重
     * @param used 全排列used数组，用于标记哪些点已使用过
     * @param level 用于记录排列的长度
     */
    public static void dfs(int pre, int sum, boolean[] used, int level) {
        if (level == n) {
            // 此时pre是最后一个客户所在点，送完最后一个客户后，快递员需要回到快递站，因此最终累计路径权重为 sum + dist[pre][0]
            // 我们保留最小权重路径
            ans = Math.min(ans, sum + dist[pre][0]);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            dfs(i, sum + dist[pre][i], used, level + 1);
            used[i] = false;
        }
    }
}