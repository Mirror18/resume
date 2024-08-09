package com.mirror.数组组成的最小数字;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] strs = scanner.nextLine().split(",");

        System.out.println(getResult(strs));
    }

    private static String getResult(String[] strs) {
        Arrays.sort(strs, (a,b) -> Integer.parseInt(a) - Integer.parseInt(b));

        String[] tmp = Arrays.copyOfRange(strs, 0, Math.min(3, strs.length));

        Arrays.sort(tmp, (a,b) -> (a +b).compareTo(b + a));

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : tmp) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }
}
