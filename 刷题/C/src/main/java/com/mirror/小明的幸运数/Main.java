package com.mirror.小明的幸运数;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if(n < 1 || n > 100){
            System.out.println("12345");
            return;
        }

        int m = scanner.nextInt();

        if(m < -100 || m > 100){
            System.out.println("12345");
            return;
        }

        int pos = 0;
        int maxPos = 0;

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();

            if(num < -100 || num > 100){
                System.out.println("12345");
                return;
            }
            pos += num;

            if(num == m){
                if(num > 0){
                    pos +=1;
                }else if(num < 0){
                    pos -= 1;
                }
            }

            maxPos = Math.max(maxPos,pos);
        }

        System.out.println(maxPos);
    }
}
