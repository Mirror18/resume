package com.mirror.游戏分组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int[] arr = new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i] = scanner.nextInt();
//        }
//
//        System.out.println(getResult(arr));
//    }
//
//    private static int getResult(int[] arr) {
//        Arrays.sort(arr);
//        //用来存储所有的分组结果
//        ArrayList<Integer> res = new ArrayList<>();
//        //采用深度优先搜索，将
//        dfs(arr, 0, 0, 0, res);
//        //实力总和
//        int sum = Arrays.stream(arr).reduce(Integer::sum).orElse(0);
//        //另一队为sum - subsum.两者差值为 -subSum。然后求这个的最小值
//        return res.stream().map(subSum -> Math.abs(sum - 2 * subSum)).min((a,b) -> a - b).orElse(0);
//    }
//
//    /**
//     *
//     * @param arr 实力数组
//     * @param index  人员下标
//     * @param level  已经有几位队员
//     * @param sum 实力总和
//     * @param res 最后组合添加
//     */
//    private static void dfs(int[] arr, int index, int level, int sum, ArrayList<Integer> res) {
//
//        if(level == 5){
//            res.add(sum);
//            return;
//        }
//
//        for (int i = index; i < 10; i++) {
//            //这里是对深度搜索遍历做的优化，即进行去重
//            if(i > index && arr[i] == arr[i-1]){
//                continue;
//            }
//            dfs(arr,i+1,level+1,sum+arr[i],res);
//        }
//    }
//}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(getResult(arr));
    }

    private static int getResult(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        int target = totalSum  / 2;
        int n = arr.length;

        boolean[] dp = new boolean[target +1];

        dp[0] = true;

        for (int score :arr) {
            for (int i = target; i >= score; i--){
                dp[i] = dp[i] || dp[i - score];
            }
        }

        int group1Sum = 0;
        for (int i = target; i >= 0; i --) {
            if(dp[i]){
                group1Sum = i;
                break;
            }
        }

        int group2Sum = totalSum - group1Sum;
        return Math.abs(group1Sum - group2Sum);
    }
}