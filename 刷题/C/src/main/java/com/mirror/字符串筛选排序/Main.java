package com.mirror.字符串筛选排序;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        int k = scanner.nextInt();

        System.out.println(getResult(str,k));
    }

    private static int getResult(String str, int k) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);;

        if(k > str.length()){
            k = str.length();
        }

        char tar = chars[k-1];
        return str.indexOf(tar);
    }
}
