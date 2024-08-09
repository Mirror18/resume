package com.mirror.滑动窗口最大和;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();

        int[] sums = new int[n - m + 1];
        int temp = 0;
        for (int i = 0; i < m; i++) {
            temp += a[i];
        }
        sums[0] = temp;
        for (int i = 1; i < n - m + 1; i++) {
            sums[i] = temp + a[i + m -1] - a[i - 1];
            temp = sums[i];
        }
        System.out.println(Arrays.stream(sums).max().getAsInt());
    }
}
