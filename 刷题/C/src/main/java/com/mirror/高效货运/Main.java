package com.mirror.高效货运;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wa = scanner.nextInt();
        int wb = scanner.nextInt();
        int wt = scanner.nextInt();
        int pa = scanner.nextInt();
        int pb = scanner.nextInt();

        int minX = 1;
        int maxX = (wt - wb) / wa;

        int ans = 0;

        for (int x = minX; x <= maxX; x++) {
            int remain = wt - wa * x;
            if (remain % wb == 0) {
                int y = remain / wb;

                ans = Math.max(ans, pa * x + pb * y);
            }
        }
        System.out.println(ans);
    }
}
