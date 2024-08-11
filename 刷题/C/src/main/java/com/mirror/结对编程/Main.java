package com.mirror.结对编程;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] levels = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n, levels));
    }

    public static long getResult(int n, int[] levels) {
        long ans = 0;

        for (int i = 1; i < n - 1; i++) {
            int mid = levels[i];

            long leftSmallerCount = 0;
            long leftBiggerCount = 0;

            for (int j = 0; j < i; j++) {
                if (levels[j] > mid) {
                    leftBiggerCount++;
                } else {
                    leftSmallerCount++;
                }
            }

            long rightSmallerCount = 0;
            long rightBiggerCount = 0;

            for (int k = i + 1; k < n; k++) {
                if (levels[k] > mid) {
                    rightBiggerCount++;
                } else {
                    rightSmallerCount++;
                }
            }

            ans += leftSmallerCount * rightBiggerCount + leftBiggerCount * rightSmallerCount;
        }

        return ans;
    }
}