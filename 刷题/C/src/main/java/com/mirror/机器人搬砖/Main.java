package com.mirror.机器人搬砖;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] bricks = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(bricks));

    }

    private static int getResult(int[] bricks) {
        int n = bricks.length;
        if (n > 8) {
            return -1;
        }
        Arrays.sort(bricks);
        int max = bricks[n - 1];
        int min = bricks[0];

        if (n == 8) {
            return max;
        }
        int ans = 0;
        while (min <= max) {
            int mid = (max + min) /2 ;
            if (check(mid, 8, bricks)) {
                ans = mid;
                max = mid -1;
            } else {
                min = mid +1;
            }
        }
        return ans;
    }

    private static boolean check(int mid, int i, int[] bricks) {
        int cost = 0;
        for (int brick : bricks) {
            cost += brick / mid + (brick % mid > 0 ? 1 : 0);

            if (cost > i) {
                return false;
            }
        }
        return true;
    }
}
