package com.mirror.反射计数;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //列数
        int w = scanner.nextInt();
        //行数
        int h = scanner.nextInt();
        //初始坐标
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        //速度
        int sx = scanner.nextInt();
        int sy = scanner.nextInt();
        //时间
        int t = scanner.nextInt();

        char[][] matrix = new char[h][w];
        for (int i = 0; i < h; i++) {
            matrix[i] = scanner.next().toCharArray();
        }

        int ans = 0;

        while (t >= 0) {
            if (matrix[y][x] == '1') {
                ans++;
            }

            y += sy;
            x += sx;

            if (x <= 0) {
                x = 1;
                sx = -sx;
            } else if (x >= w) {
                x = w - 2;
                sx = -sx;
            }

            if (y < 0) {
                y = 1;
                sy = -sy;
            } else if (y >= h) {
                y = h - 2;
                sy = -sy;
            }
            t--;
        }
    }
}
