package com.mirror.欢乐的周末;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 长度m表示行数
        int m = sc.nextInt();
        // 宽度n表示列数
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println(getResult(matrix));
    }

    public static int getResult(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        UnionFindSet ufs = new UnionFindSet(rows * cols);
        // 记录小华，小为的位置
        ArrayList<Integer> huawei = new ArrayList<>();
        // 记录餐厅的位置
        ArrayList<Integer> restaurants = new ArrayList<>();
        // 上下左右四个方向偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 1) {
                    // 二维坐标(i, j) 转为 一维坐标pos
                    int pos = i * cols + j;

                    if (matrix[i][j] == 2) {
                        // 收集小华，小为的位置
                        huawei.add(pos);
                    } else if (matrix[i][j] == 3) {
                        // 收集餐厅的位置
                        restaurants.add(pos);
                    }
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && matrix[newI][newJ] != 1) {
                            // 如果(i,j)和（newI,newJ）位置都是非1，则合并
                            ufs.union(pos, newI * cols + newJ);
                        }
                    }
                }
            }
        }

        // 小华所在连通分量的根
        int hua_fa = ufs.find(huawei.get(0));
        // 小为所在连通分量的根
        int wei_fa = ufs.find(huawei.get(1));

        // 如果小华和小为的不属于同一个连通分量，则二人无法去往相同餐厅
        if (hua_fa != wei_fa) {
            return 0;
        }

        // 找出和小华、小为在同一个连通分量里面的餐厅
        int ans = 0;
        for (Integer restaurant : restaurants) {
            if (ufs.find(restaurant) == hua_fa) {
                ans++;
            }
        }

        return ans;
    }
}

// 并查集实现
class UnionFindSet {
    int[] fa;

    public UnionFindSet(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
        }
    }
}
