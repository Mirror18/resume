package com.mirror.会议室占用时间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取输入
        int n = scanner.nextInt();
        //转换成二维数组
        int[][] roomTimes = new int[n][2];
        for (int i = 0; i < n; i++) {
            roomTimes[i][0] = scanner.nextInt();
            roomTimes[i][1] = scanner.nextInt();
        }
        //处理数组
        int[][] res = merge(roomTimes);
        //打印数组
        for (int[] time : res) {
            System.out.println(time[0] + " " + time[1]);
        }
    }

    private static int[][] merge(int[][] roomTimes) {
        //对数组进行排序
        //将各个会议按照开始时间升序
        Arrays.sort(roomTimes, (a,b) -> a[0] - b[0]);
        //动态数组
        //记录合并后的会议室占用时间段
        ArrayList<int[]> list = new ArrayList<>();
        //上一个会议占用的时间
        int[] pre = roomTimes[0];
        for (int i = 0; i < roomTimes.length; i++) {
            //当前会议占用的时间
            int[] cur = roomTimes[i];

            if(cur[0] <= pre[1]){
                //当前会议开始时间，上一个会议结束的时间
                //合并时候，结束时间取两个时间段中较大的结束时间
                pre[1] = Math.max(pre[1], cur[1]);
            }else{
                //否则就不合并
                list.add(pre);
                pre = cur;
            }
        }
        list.add(pre);

        return list.toArray(new int[0][]);
    }
}
