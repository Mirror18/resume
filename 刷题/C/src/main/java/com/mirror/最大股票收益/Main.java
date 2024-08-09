package com.mirror.最大股票收益;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .map(
                        p -> {
                            int num = Integer.parseInt(p.substring(0, p.length() -1));
                            String unit = p.substring(p.length() -1);
                            return "Y".equals(unit) ? num: num*7;
                        }
                )
                .toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    private static int getResult(Integer[] arr) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans += Math.max(0,arr[i] - arr[i -1]);
        }
        return ans;
    }
}
