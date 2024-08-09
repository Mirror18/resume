package com.mirror.查找接口成功率最优的时间段;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        //获取输入
        Scanner scanner = new Scanner(System.in);
        int minAverageLost = Integer.parseInt(scanner.nextLine());
        int[] a1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //处理数据
        System.out.println(getResult(a1, minAverageLost));
    }

    private static String getResult(int[] nums, int minAverageLost) {
        //获取输入的数组长度
        int n = nums.length;
        //前缀之和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        ArrayList<int[]> ans = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = preSum[j] - preSum[i];
                int len = j - i;
                //通过把相除变为相乘来验证结果
                int lost = len * minAverageLost;
                //当在范围区间内
                if (sum <= lost) {
                    //当大于最大区间才能添加
                    if (len >= maxLen) {
                        if (len > maxLen) {
                            ans = new ArrayList<>();
                        }
                        ans.add(new int[]{i, j - 1});
                        maxLen = len;
                    }
                }
            }
        }
        if (ans.size() == 0) {
            return "NULL";
        }
        ans.sort((a, b) -> a[0] - b[0]);

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int[] an : ans) {
            stringJoiner.add(an[0] + "-" + an[1]);
        }
        return stringJoiner.toString();
    }
}
