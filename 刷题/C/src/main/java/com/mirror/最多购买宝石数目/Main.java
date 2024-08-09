package com.mirror.最多购买宝石数目;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] gems = new int[n];

        for (int i = 0; i < n; i++) {
            gems[i] = scanner.nextInt();
        }

        int v = scanner.nextInt();

        int ans = 0;
        //左右指针
        int l = 0;
        int r = 0;

        int window_sum = 0;

        outer:
        while( r< n){
            window_sum += gems[r];

            if(window_sum <=v){
                r++;
            }else{
                ans = Math.max(ans, r- l);
                //这里移动左指针，
                while( l<r){
                    window_sum -= gems[l++];

                    if(window_sum <=v){
                        r++;
                        continue outer;
                    }
                }
                //因为左右指针都移动过，所以这里新开屁一个
                l = ++r;
                window_sum = 0;
            }
        }
        //这里指的是到末尾后，依旧在添加，所以需要总和一下
        if(window_sum <= v){
            ans = Math.max(ans,r-l);
        }
        System.out.println(ans);
    }
}
