package com.mirror.CPU算力分配;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            int a1 = scanner.nextInt();
            int a2 = scanner.nextInt();


            int  sumA = 0, sumB = 0;

            int[] A = new int[a1];

            for (int i = 0; i < a1; i++) {
                int a = scanner.nextInt();
                sumA += a;
                A[i] = a;
            }
            HashSet<Integer> setB = new HashSet<>();
            for (int i = 0; i < a2; i++) {
                int a = scanner.nextInt();
                sumB += a;
                setB.add(a);
            }
            int half_diff = (sumA - sumB) / 2;

            int minA = Integer.MIN_VALUE;
            String ans = " ";

            for (int a : A) {
                int b = a - half_diff;

                if (setB.contains(b)) {
                    if (a < minA) {
                        minA = a;
                        ans = a + " " + b;
                    }
                }
            }
            System.out.println(ans);

        }

    }
}
