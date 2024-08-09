package com.mirror.求解连续数列;

import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int s = scanner.nextInt();
        int n = scanner.nextInt();


        System.out.println(getResult(s,n));
    }

    private static String getResult(int s, int n) {
        int mid = s /n ;
        int left, right ,half;

        if(n %2 ==0){

            half = n >>1;
            left = mid - half + 1;
        }else{
            half = ( n -1) / 2;
            left = mid - half;
        }
        right = mid + half;

        StringJoiner stringJoiner = new StringJoiner(" ");
        int total = 0;
        for (int i = left; i <= right; i++) {
            stringJoiner.add(i + "");
            total += i;

        }
        if (total == s){
            return stringJoiner.toString();
        }else{
            return "-1";
        }

    }
}
