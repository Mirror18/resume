package com.mirror.整数对最小和;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1, n2;
        int[] arr1, arr2;

        n1 = scanner.nextInt();
        arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = scanner.nextInt();
        }

        n2 = scanner.nextInt();
        arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();

        System.out.println(getResult(arr1, arr2, k));
    }

    private static int getResult(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null || k <= 0) {
            return 0;
        }
        //创建小根堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < arr2.length; i++) {
            minHeap.offer(new int[]{arr1[0] + arr2[i], 0, i});
        }

        int result = 0;
        int count = 0;
        while (!minHeap.isEmpty() && count < k) {
            int[] current = minHeap.poll();
            result += current[0];
            count++;
            int i = current[1];
            int j = current[2];
            if (i + 1 < arr1.length) {
                minHeap.offer(new int[]{arr1[i + 1] + arr2[j], i+1,j});

            }
        }
        return  result;
    }
}
