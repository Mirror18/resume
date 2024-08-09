package com.mirror.找朋友;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]  =scanner.nextInt();
        }

        System.out.println(getResult(arr,n));
    }

    private static String getResult(int[] arr,int n) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[n];

        for (int i = n-1;i>=0; i--) {
            while(!stack.isEmpty() && arr[stack.getLast()] <= arr[i]){
                stack.removeLast();
            }

            if(!stack.isEmpty()){
                result[i] = stack.getLast() ;
            }else{
                result[i] = 0;
            }
            stack.addLast(i);

        }
        return Arrays.toString(result);
    }
}
