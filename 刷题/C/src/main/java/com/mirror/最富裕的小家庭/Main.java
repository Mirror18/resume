package com.mirror.最富裕的小家庭;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long[] wealth = new long[n+1];
        long[] family = new long[n+1];

        for (int i = 1; i <= n ; i++) {
            wealth[i] = scanner.nextInt();
            family[i] = wealth[i];
        }

        for (int i = 0; i < n - 1; i++) {
            int fa = scanner.nextInt();
            int ch = scanner.nextInt();
            family[fa] += wealth[ch];
        }
        System.out.println(Arrays.stream(family).max().orElse(0));
    }
}
