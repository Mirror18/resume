package com.mirror.数组连续和;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int x = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(getResult(n,x,arr));
    }

    private static long getResult(int n, int x, int[] arr) {
        int[] preSum = new int[n + 1];

        //这是求解前缀和，
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + arr[i-1];

        }
        //这里就复杂了
        //首先解释的是这俩为什么初始化为0和1，因为题目中允许有单个区间，所以差值为1
        int l = 0;
        int r = 1;
        long ans = 0;
        while( r<= n){
            //至于这里，则是因为前缀和一定是升序队列，所以当当前区间差满足要求的时候
            //例如现在是 l 到 r,那么从 l 到 n是不是也可以允许，那么这其中的个数是多少呢
            //n -r + 1
            //这玩意儿类似于一个窗口，用窗口解决问题，但是窗口数不确定
            if(preSum[r] - preSum[l] >= x){
                ans += n -r + 1;
                //这一块类似于窗口重置
                l++;
                r = l +1;
            }else{

                r++;
            }
        }
        return ans;
    }
}
