package com.mirror.考勤信息;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //几个员工进行考勤统计
        int n = Integer.parseInt(scanner.nextLine());

        //记录考勤信息
        String[][] records = new String[n][];
        for (int i = 0; i < n; i++) {
            records[i] = scanner.nextLine().split(" ");
        }

        getResult(n, records);
    }

    private static void getResult(int n, String[][] records) {
        //打印每个员工是否有考勤奖
        for (int i = 0; i < n; i++) {
            System.out.println(isAward(records[i]));
        }
    }

    private static boolean isAward(String[] record) {
        //缺勤次数
        int absent = 0;
        //正常次数
        int present = 0;
        //上一次的记录
        String preRecord = " ";
        for (int i = 0; i < record.length; i++) {
            if (i >= 7) {
                //因为要记录连续七天是否有正常之外的，所以这里用了窗口
                //窗口数量为7，当超过的时候，边界左侧需要右移，所以根据
                //左边界的信息来更新present的次数
                if ("present".equals(record[i - 7])) {
                    present--;
                }
            }
            //当前的考勤记录
            String curRecord = record[i];

            //根据考勤记录结果进行处理
            //这里利用switch的泄露曾测进行处理
            switch (curRecord) {
                case "absent":
                    if (++absent > 1) {
                        return false;
                    }
                case "late":
                case "leaveearly": {
                    //这里处理的是连续迟到的结果
                    if ("late".equals(preRecord) || "leaveearly".equals(preRecord)) {
                        return false;
                    }
                    break;
                }
                case "present": {
                    present++;
                    break;
                }

            }
            //进行更新
            preRecord = curRecord;

            //这里则是判断在这连续七天的考勤中，
            //或者说叫探测的长度中，别有三次即可。实话说窗口长度最小也是5
            int window_len = Math.min(i + 1, 7);
            if(window_len - present > 3){
                return  false;
            }
        }
        return true;
    }
}
