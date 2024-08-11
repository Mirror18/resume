package com.mirror.可以组成网络的服务器;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult());
    }

    public static int getResult() {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        return ans;
    }

    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int bfs(int i, int j) {
        LinkedList<int[]> queue = new LinkedList<>();

        int count = 1;
        matrix[i][j] = 0;

        queue.add(new int[] {i, j});

        while (queue.size() > 0) {
            int[] pos = queue.removeFirst();

            int x = pos[0];
            int y = pos[1];

            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m && matrix[newX][newY] == 1) {
                    count++;
                    matrix[newX][newY] = 0;
                    queue.add(new int[] {newX, newY});
                }
            }
        }

        return count;
    }
}