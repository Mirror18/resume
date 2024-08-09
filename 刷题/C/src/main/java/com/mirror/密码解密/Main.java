package com.mirror.密码解密;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split("\\*")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(arr).forEach(a-> System.out.print((char) (a+'a' -1) + ""));
    }
}


//public class Main {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        String s = sc.nextLine();
//
//        for (int i = 26; i >= 1; i--) {
//            String key = i + (i > 9 ? "\\*" : "");
//            String val = String.valueOf((char) ('a' + i - 1));
//            s = s.replaceAll(key, val);
//        }
//
//        System.out.println(s);
//    }
//}