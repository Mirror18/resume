package com.mirror.按照身高和体重排队;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        //用于接受数据成为数组
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
//接入处理
        System.out.println(getResult(n, heights, weights));
    }

    private static String getResult(int n, int[] heights, int[] weights) {
        int[][] students = new int[n][3];
        for (int i = 0; i < n; i++) {
            //添加权重
            students[i] = new int[]{heights[i], weights[i], i + 1};
        }
        Arrays.sort(
                //排序规则，传入的是student数组，先比较第一权重
                //如果第一权重不想等，则按照第一权重排序，从小到大
                //当第一权重想等的情况下排序第二权重
                //想等的情况下则就按照原有的位次排序
                students, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]
        );
        //这是拼装
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int[] student : students) {
            stringJoiner.add(student[2] + "");
        }
        return stringJoiner.toString();
    }
}
