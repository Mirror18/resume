package com.mirror.停车场车辆统计;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str =
                scanner.nextLine()
                        .replaceAll(",","")
                        .replaceAll("111","x")
                        .replaceAll("11","x")
                        .replaceAll("1","x");

        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'x'){
                ans++;
            }
        }

        System.out.println(ans);
    }
}
