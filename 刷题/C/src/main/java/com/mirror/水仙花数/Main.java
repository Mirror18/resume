package com.mirror.水仙花数;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println( getResult(n,m));
    }

    /**
     * 关于水仙花数，举例， 153 = 1 ^3 + 5^ 3 + 3^3
     * @param n 总位数
     * @param m 这区间内的第几个水仙花数
     * @return
     */
    private static long getResult(int n, int m) {
        //这是题目中的限制范围
        if( n < 3 || n > 7){
            return  -1;
        }
        //首先进行次方计算
        HashMap<Character, Integer> powN = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            powN.put((char) (i+'0'),(int) Math.pow(i,n));
        }
        //这个区间内的最小值
        int min = (int) Math.pow(10, n - 1);
        //这个区间内的最大值
        int max = (int) Math.pow(10,n);

        long ans = 0;
        //记录当前水仙花位数是第几个
        int idx = 0;
        //求解这个区间内所有的水仙花数
        for (int num = min; num < max; num++) {
            //用于位数
            int sum = 0;
            //将其转换为字符串
            String str = num + "";
            for (int i = 0; i < n; i++) {
                //字符串拼接
                sum += powN.get(str.charAt(i));
            }

            if(sum == num){
                ans = num;

                if(idx++ == m){
                    return  ans;
                }

            }
        }
        //如果这个区间内没有这个水仙花数，对其进行的处理
        return ans * m;
    }
}
