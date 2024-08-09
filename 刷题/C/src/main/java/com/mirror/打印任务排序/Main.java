package com.mirror.打印任务排序;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] priority = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(priority));
    }

    private static String getResult(int[] priority) {

        int n = priority.length;

        LinkedList<int[]> link = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            link.add(new int[]{priority[i], i});
        }
        Arrays.sort(priority);

        int maxI = n - 1;
        //用于存储打印顺序，有相互照应的位
        int[] ans = new int[n];
        //打印顺序
        int printIdx = 0;

        while (link.size() > 0) {
            int[] tmp = link.removeFirst();
            int p = tmp[0], i = tmp[1];

            if (p == priority[maxI]) {
                ans[i] = printIdx;
                printIdx++;
                maxI--;
            } else {
                link.add(tmp);
            }
        }

        StringJoiner stringJoiner = new StringJoiner(",");

        for (int an : ans) {
            stringJoiner.add(an + "");
        }
        return stringJoiner.toString();
    }
}
