package com.mirror.快递运输;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] weights = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int limit = Integer.parseInt(scanner.nextLine());

        System.out.println(getResult(weights,limit));
    }

    private static int getResult(int[] weights,int limit) {
        Arrays.sort(weights);

        int count = 0;
        int sum = 0;
        for (int w : weights) {
            sum += w;
            if(sum > limit){
                break;
            }
            count++;
        }
        return  count;
    }
}
