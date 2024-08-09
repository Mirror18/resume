package com.mirror.小华地图寻宝;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    //行数
    static int m;
    //列数
    static int n;
    //阈值
    static int k;
    //答案
    static int ans = 0;
    //是否已存在
    static HashSet<Integer> visited = new HashSet<>();
    //偏移坐标
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //缓存，缓存纵坐标和横坐标位数和
    static int[] digitSums;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        m = scanner.nextInt();
        n = scanner.nextInt();
        k = scanner.nextInt();
        //缓存横纵坐标位数和，避免重复计算
        digitSum(Math.max(m, n));
        //深度搜索
        dfs(0, 0);
        System.out.println(ans);

//        //广度搜索
//        if(m==0|| n==0){
//            System.out.println(0);
//        }else{
//            System.out.println(bfs());
//        }

    }

    private static void dfs(int x, int y) {
        //这是对应位置越界，已经访问过，或者超过阈值
        if (x < 0
                || x >= m
                || y < 0
                || y >= n
                || visited.contains(x * n + y)
                || digitSums[x] + digitSums[y] > k
        ) {
            return;
        }
        //没访问过就进行访问
        visited.add(x * n + y);
        ans++;
        //对四个方向进行搜索
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];

            dfs(newX, newY);
        }
    }

    private static void digitSum(int maxSize) {
        //首先创造出一个数字的数位和，进行缓存
        digitSums = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            int num = i;
            //重复计算取得数位和
            while (num > 0) {
                digitSums[i] += num % 10;
                num /= 10;
            }
        }
    }

    /**
     * 广度搜索
     *
     * @return
     */
    public static int bfs() {
        //将二维数组变为一条线
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(0);

        int ans = 1;
        visited.add(0);

        while (queue.size() > 0) {
            int pos = queue.removeFirst();
            int x = pos / n;
            int y = pos % n;

            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];

                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }

                if (digitSums[newX] + digitSums[newY] > k) {
                    continue;
                }

                int newPos = newX * n + newY;
                if (visited.contains(newPos)) {
                    continue;
                }

                ans++;
                visited.add(newPos);
                queue.addLast(newPos);
            }
        }
        return ans;
    }
}
