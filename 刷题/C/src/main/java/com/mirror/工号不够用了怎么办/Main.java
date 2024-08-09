package com.mirror.工号不够用了怎么办;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        System.out.println((int) Math.max(1, Math.ceil(Math.log10(n / Math.pow(26, a)))));
    }

}
