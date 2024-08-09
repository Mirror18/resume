package com.mirror.求符合要求的结对方式;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = scanner.nextInt();

        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();

        System.out.println(getResult(arr,n));
    }

    /**
     * 算是双循环暴力求解
     * 但是在求解进行了优化，首先是进行排序，这样可以用最大和最小的进行结合
     * 并且当值，<n 的时候 可以提前结束
     * 内循环结束的条件也可以设为在此之前，防止重复添加
     * 如果是单纯的暴力求解的话，答案是需要/2的。
     * @param arr
     * @param n
     * @return
     */
    private static int getResult(int[] arr, int n) {

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length -1; j >= i +1; j--) {
                int sum = arr[i] +arr[j];
                if(sum == n){
                    ans++;
                }
                else if(sum < n){
                    break;
                }
            }
        }
        return ans;
    }
}
