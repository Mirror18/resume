package com.mirror.快递运输;

import java.util.Arrays;

/**
 * 这是01背包中暴力求解的方式
 */
public class BagProblem {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem(weight,value,bagSize);
        testNum(weight, value, bagSize);
    }

    //一维数组进行优化
    private static void testNum(int[] weight, int[] value, int bagSize) {
        int wLen = weight.length;
        //定义dp数组，dp[j]表示背包容量为j时候，能获得的最大价值
        int[] dp = new int[bagSize + 1];
        //先遍历物品，再遍历背包容量
        for (int i = 0; i < wLen; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        Arrays.stream(dp).forEach(a -> System.out.print(a + " "));
    }

    private static void testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        //创建dp数组
        int goods = weight.length;//获取物品重量
        int[][] dp = new int[goods][bagSize + 1];
        //初始化dp数组
        //首先dp数组，是二维数组，值是当前的价值之和
        //i代表0 到 i 的物品放进去，j是当前背包的重量
        //根据下方的递推关系式来看，是需要初始化第一行和第一列的
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        //填充dp数组
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                //当前背包的容量没有物品i大的时候，是不放的
                //那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //当前背包能放下物品i
                    //那么此时最大的价值不放当前物品
                    //要么是在放置当前物品，但是需要腾出空间
                    //即不放当前物品，并且有空间的地方加上这个价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        //打印dp数组，当然也可以输出特定值的情况
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
