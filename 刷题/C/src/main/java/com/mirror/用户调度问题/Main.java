package com.mirror.用户调度问题;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] res = new int[n][3];

        for (int i = 0; i < n; i++) {
            res[i][0] = scanner.nextInt();
            res[i][1] = scanner.nextInt();
            res[i][2] = scanner.nextInt();
        }

        System.out.println(getResult(n,res));
    }

    private static int getResult(int n, int[][] res) {
        //这是上次选择的结果
        int last = -1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            last = getMinEleIdx(res[i],last);
            sum += res[i][last];
        }
        return sum;
    }

    private static int getMinEleIdx(int[] arr, int last) {
        int minEleVal = Integer.MAX_VALUE;
        int minEleIdx = -1;
        //查找数组中最小值，排除上一次的查找结果
        for (int i = 0; i < arr.length; i++) {
            if( i == last){
                continue;
            }
            if(arr[i] <= minEleVal){
                minEleVal = arr[i];
                minEleIdx = i;
            }
        }
        return minEleIdx;
    }
}
