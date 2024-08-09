package com.mirror.翻牌求最大分;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取输入
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //处理结果
        System.out.println(getResult(a));
    }

    private static int getResult(int[] a) {
        int n = a.length;
        //用于存储在当前的总得分
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            //一到三次的翻牌数＜0 就取 0
            if(i == 0){
                dp[0] = Math.max(0, a[0]);
            }
            //处理dp中的 1 和 2
            //当前牌的总得分等于上一次翻牌总得分加上当前拍的数字
            else if(i < 3){
                dp[i] = Math.max(0, dp[i -1] + a[i]);
            }
            //如果＜三次前的翻牌分，则就取三次前的分
            else{
                dp[i] = Math.max(dp[i-3], dp[i -1] + a[i]);
            }
        }
        //返回最后一次的得分
        return  dp[n -1];
    }
}
