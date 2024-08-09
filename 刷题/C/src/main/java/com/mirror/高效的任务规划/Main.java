package com.mirror.高效的任务规划;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();

        int[][][] tasks = new int[m][][];

        for (int i = 0; i < m; i++) {
            int n = scanner.nextInt();
            int[][] task  = new int[n][2];
            for (int j = 0; j < n; j++) {
                task[j][0] = scanner.nextInt();
                task[j][1] = scanner.nextInt();
            }

            tasks[i] = task;
        }

        getResult(tasks);
    }

    private static void getResult(int[][][] tasks) {

        for (int[][] task :
                tasks) {
            Arrays.sort(task, (a, b) -> b[1] - a[1]);

            int config_endTime = 0;
            int ans = 0;
            for (int[] info :
                    task) {
                int config_cost = info[0];
                int run_cost = info[1];

                config_endTime += config_cost;
                ans = Math.max(ans,config_endTime + run_cost);
            }

            System.out.println(ans);
        }
    }
}
