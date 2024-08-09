package com.mirror.字符串序列判定;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        String l = scanner.nextLine();

        System.out.println(getResult(s, l));
    }

    private static int getResult(String s, String l) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < l.length()) {
            if (s.charAt(i) == l.charAt(j)) {
                i++;
            }
            j++;
        }
        if (i == s.length()) {
            return j - 1;
        } else {
            return -1;
        }
    }
}
