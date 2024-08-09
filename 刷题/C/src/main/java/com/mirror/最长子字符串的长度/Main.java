package com.mirror.最长子字符串的长度;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
    }

    private static int getResult(String s) {
        int n = s.length();

        int zeroCount = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'o') {
                zeroCount++;
            }
        }
        if (zeroCount % 2 == 0) {
            return n;
        } else {
            return n - 1;
        }
    }

}
