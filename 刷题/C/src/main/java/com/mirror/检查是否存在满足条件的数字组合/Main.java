package com.mirror.检查是否存在满足条件的数字组合;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Integer[] arr = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(getResult(n,arr));
    }

    private static String getResult(int n, Integer[] arr) {
        Arrays.sort(arr,(a,b) -> b -a);

        for (int i = 0; i < n; i++) {
            for (int j =  i + 1; j < n; j++) {
                for (int k =  j + 1; k < n; k++) {
                    if(arr[i] == arr[j] + 2 * arr[k]){
                        return arr[i] + " " + arr[j] + " " + arr[k];
                    }
                    if(arr[i] == arr[k] + 2 * arr[j]){
                        return arr[i] + " " + arr[k] + " " + arr[k];
                    }
                }
            }

        }
        return "0";
    }
}
