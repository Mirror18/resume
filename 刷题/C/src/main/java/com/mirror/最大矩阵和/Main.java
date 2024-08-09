package com.mirror.最大矩阵和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getResult(n, m, matrix));
    }

    private static int getResult(int n, int m, int[][] matrix) {
        ArrayList<Integer> dp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //一行子矩阵最大和
            dp.add(maxSubArraySum(matrix[i]));
            //多行子矩阵最大和
            for (int j = i + 1; j < n; j++) {
                dp.add(maxSubArraySum(matrixZip(Arrays.copyOfRange(matrix, i, j + 1))));
            }
        }
        //求出最大和
        return dp.stream().max((a, b) -> a - b).orElse(0);
    }

    private static Integer maxSubArraySum(int[] nums) {
        int[] dp = new int[nums.length];

        int res = dp[0] = nums[0];
        //只要前面不为0，就进行相加
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    //多行子矩阵，压缩为一行子数组
    private static int[] matrixZip(int[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        int[] zip = new int[cols];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                zip[i] += matrix[j][i];
            }
        }
        return zip;
    }
}
