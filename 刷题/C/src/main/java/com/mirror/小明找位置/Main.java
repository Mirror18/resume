package com.mirror.小明找位置;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(scanner.nextLine());

        int idx = Arrays.binarySearch(nums,target);

        if(idx <0){
            idx = -idx -1;
        }

        System.out.println(idx +1);
    }
}
