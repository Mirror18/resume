package com.mirror.a5G网络建设;

import java.util.Arrays;
import java.util.Scanner;

public class Kruskal {
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int p = scanner.nextInt();

            edges[i] = new Edge(x, y, p == 0 ? z : 0);
        }

        System.out.println(kruskal(edges, n));
    }

    private static int kruskal(Edge[] edges, int n) {

        int minWeight = 0;

        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        UnionFindSet ufs = new UnionFindSet(n + 1);
        for (Edge edge : edges) {
            if (ufs.find(edge.from) != ufs.find(edge.to)) {
                minWeight += edge.weight;
                ufs.union(edge.from, edge.to);
                //这是处理有多少个连通分量，0是单独算一个，如果有俩个，那证明全添加进去了
                if (ufs.count == 2) {
                    return minWeight;
                }
            }

        }
        return -1;
    }

    private static class UnionFindSet {
        int[] fa;
        int count;

        public UnionFindSet(int n) {
            this.fa = new int[n];
            this.count = n;

            for (int i = 0; i < n; i++) {
                this.fa[i] = i;
            }
        }

        public int find(int x) {
            if (x != this.fa[x]) {
                return (this.fa[x] = this.find(this.fa[x]));
            }
            return x;
        }

        public void union(int x, int y) {
            int x_fa = this.find(x);
            int y_fa = this.find(y);

            if (x_fa != y_fa) {
                this.fa[y_fa] = x_fa;
                this.count--;
            }
        }
    }
}
