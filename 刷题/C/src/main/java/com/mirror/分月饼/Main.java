package com.mirror.分月饼;

import java.util.Scanner;

public class Main {
    static int m;
    static int n;
    static int maxDiff = 3;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        m = scanner.nextInt();
        n = scanner.nextInt();

        if (m == 1) {
            System.out.println(1);
        } else {
            recursive(0, 1, n / m, n);
            System.out.println(ans);
        }
    }

    private static void recursive(int level, int min, int max, int remain) {
        if (level == m - 1) {
            if (remain - min <= maxDiff) {
                ans++;
            }
            return;
        }
        //每次循环的是分给第一个人的数量。因为没有固定顺序，所以第一个最多为m / n
        for (int i = min; i <= max; i++) {
            remain -= i;
            recursive(level + 1, i, Math.min(i + maxDiff, remain / (m - level - 1)), remain);
            remain += i;
        }
    }
}
