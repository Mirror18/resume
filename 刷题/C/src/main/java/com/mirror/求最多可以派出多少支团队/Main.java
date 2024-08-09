package com.mirror.求最多可以派出多少支团队;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] capacitites = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minCap = Integer.parseInt(scanner.nextLine());

        System.out.println(getResult(n,capacitites,minCap));
    }

    private static int getResult(int n, int[] capacitites, int minCap) {

        Arrays.sort(capacitites);
        int l = 0;
        int r = n -1;
        int ans = 0;
        while (r>=l && capacitites[r] >= minCap){
            r--;
            ans++;
        }
        while( l < r){
            int sum = capacitites[l] + capacitites[r];

            if(sum >= minCap){
                ans++;
                l++;
                r--;
            }else{
                l++;
            }
        }
        return ans;
    }
}
