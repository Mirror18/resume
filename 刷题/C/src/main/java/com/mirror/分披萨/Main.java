package com.mirror.分披萨;

import java.util.Scanner;

public class Main {
    static int[] pizza;

    //新增的缓存
    static long[][] cache;
    public static void main(String[] args) {
        //获取到输入，并将其存入到数组中
        Scanner scanner = new Scanner(System.in);
        //奇数
        int n = scanner.nextInt();
        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = scanner.nextInt();
        }
        //缓存创建
        cache = new long[n][n];
        //吃货能获得的最大披萨大小
        long ans = 0;
        //就是时间上太长，因为外部for循环，内部还要迭代每次的数组数据
        //每次都是一个结果，那么就选择从不同的地方进行检查，切口位置循环检查
        for (int i = 0; i < n; i++) {
            //对每个元素进行迭代检查
            //计算不同缺口下最后能获取的最大量，最后加上当前的披萨量
            ans = Math.max(ans, recursive(check(i - 1), check(i + 1)) + pizza[i]);
        }
    }

    private static long recursive(int l, int r) {
        //只是确保在循环内
        //这里加上判断是因为馋嘴需要每次拿走最大的，所以将位置更新下
        if (pizza[l] > pizza[r]) {
            l = check(l - 1);
        } else {
            r = check(r + 1);
        }

        //缓存优化，计算缺口状态的结果
        if(cache[l][r] > 0){
            return  cache[l][r];
        }
        //结束条件
        if (l == r) {
//            return pizza[l];
            //缓存对应缺口状态下，吃货可得的最大披萨大小
            cache[l][r]  = pizza[l];
        } else {
            //继续迭代
            //存在-1 和 +1 是因为吃货要拿走，那么剩下的需要再次进入检查
//            return Math.max(recursive(check(l - 1), r) + pizza[l], recursive(l, check(r + 1)) + pizza[r]);

            //缓存对应缺口下，吃货可得的最大披萨大小
            cache[l][r] = Math.max(recursive(check(l-1),r) + pizza[l], recursive(l,check(r + 1))+pizza[r]);
        }
        return cache[l][r];
    }
//确保成为循环数组
    private static int check(int idx) {
        if (idx < 0) {
            idx = pizza.length - 1;
        } else if (idx >= pizza.length) {
            idx = 0;
        }
        return idx;
    }
}
