package com.mirror.求幸存数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int jump = scanner.nextInt();
        int left = scanner.nextInt();

        System.out.println(sumOfLeft(nums, jump,left));
    }

    private static int sumOfLeft(int[] nums, int jump, int left) {
        ArrayList<Integer> list = (ArrayList<Integer>) Arrays.stream(nums).boxed().collect(Collectors.toList());

        int start = 1;
        //最后的数量一定是＜幸运数数量
        while( list.size() > left){
            start += jump;
            //所谓循环就是取余
            start %= list.size();
            list.remove(start);
        }

        return  list.stream().reduce(Integer::sum).orElse(0);
    }
}
