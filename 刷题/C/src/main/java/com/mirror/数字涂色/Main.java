package com.mirror.数字涂色;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n,arr));
    }

    private static int getResult(int n, int[] arr) {
        Arrays.sort(arr);
        //如果第一个数为1证明全部可以涂成一个颜色
        if(arr[0] == 1){
            return  1;
        }
        //这个用以表示是否已经被染色
        boolean[] color = new boolean[n];

        int count = 0;

        for (int i = 0; i < n; i++) {
            //如果已经染色就跳过
            if(color[i]){
                continue;
            }

            color[i] = true;
            for (int j = i + 1; j < n; j++) {
                //如果下个数没被染色，并且还能整除，那么就染色
                if(!color[j] && arr[j] % arr[i] == 0){
                    color[j] = true;
                }

            }
            count++;
        }
        return count;
    }
}
