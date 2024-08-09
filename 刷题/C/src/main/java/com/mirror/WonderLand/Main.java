package com.mirror.WonderLand;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //票价
        int[] costs = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //游玩日期
        int[] days = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //最大的游玩日期
        int maxDay = days[days.length -1];
        //动态数组，存储的是 0 到 i 天最小花费的金额
        int[] dp = new int[maxDay + 1];
        //用于指向当前需要完成的游玩日
        int index = 0;
        //从第一天开始算起，所需要的金额
        for (int i = 1; i < maxDay; i++) {
            //如果当天是游玩日
            if (i == days[index]) {
                //四种买票方式，通过给出当天四种买票的金额来进行计算最合适的方法
                int buy1 = dp[i - 1] + costs[0];

                int buy3 = (i >= 3 ? dp[i - 3] : 0) + costs[1];

                int buy7 = (i >= 7 ? dp[i - 7] : 0) + costs[2];

                int buy30 = (i >= 30 ? dp[i - 30] : 0) + costs[3];
                //选择这四种方式中最便宜的套餐
                dp[i] = Math.min(Math.min(Math.min(buy1, buy3), buy7), buy30);
                //游玩天数增加
                index++;
            } else {
                //如果没有游玩，那么现在的花费就是前一天的花费
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(dp[maxDay]);
    }
}
