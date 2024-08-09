package com.mirror.来自异国的客人;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long k = scanner.nextInt();
        long n = scanner.nextInt();
        long m = scanner.nextInt();

        System.out.println(getResult(k, n, m));
    }

    private static long getResult(long k, long n, long m) {

        if (n > m) {
            return 0;
        }

        long count = 0;

//        StringBuilder ans = new StringBuilder();

        while (k > 0) {
            long remain = k % m;
            if (remain == n) {
                count++;
            }

            k /= m;

//            ans.append(remain);

        }
        return count;
//        return Long.parseLong(ans.toString());
    }

}
