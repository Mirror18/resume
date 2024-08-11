package com.mirror.可以处理的最大任务数;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] ranges = new int[n][2];
        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt();
            ranges[i][1] = sc.nextInt();
        }
        // 将所有任务按照结束时间降序
        Arrays.sort(ranges, (a, b) -> b[1] - a[1]);
        // 优先队列中记录的是任务的开始时间，并且开始时间越大，优先级越高
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // 优先队列中记录的是结束时间相同的任务的开始时间，pq_end就是优先队列中任务的相同结束时间
        int pq_end = Integer.MAX_VALUE;

        // 最大任务数
        int count = 0;

        for (int[] range : ranges) {
            // 当前任务的开始和结束时间
            int start = range[0];
            int end = range[1];

            // 如果当前任务的结束时间 小于 优先队列中记录的任务的结束时间，则两个结束时间之间的间隔时间段，可以处理一些紧急任务
            while (pq.size() > 0 && end < pq_end) {
                // 这里的紧急任务即指时间短的任务，即开始时间比较大的任务
                if (pq.poll() <= pq_end) {
                    // 如果紧急任务的开始时间未超过其结束时间，则可以执行
                    count++;
                    pq_end--; // 一个时刻只执行一个任务
                }
            }

            // 间隔时间消耗完后，优先队列中的任务的结束时间全部更新为当前任务的结束时间
            pq.add(start);
            pq_end = end;
        }

        // 收尾处理
        while (pq.size() > 0) {
            if (pq.poll() <= pq_end) {
                count++;
                pq_end--;
            }
        }

        System.out.println(count);
    }
}