package com.mirror.转盘寿司;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //寿司价格
        int[] prices = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = prices.length;
        //答案数组
        int[] res = Arrays.copyOf(prices,n);
        //
        LinkedList<Integer> stack = new LinkedList<>();
        //分成两轮遍历，第一轮压栈，第二轮比较
        for (int i = 0; i < n * 2; i++) {
            int prices_j = prices[i % n];

            while(stack.size() > 0){
                //得到左边的价格
                int j = stack.getLast();
                //只有当左边比当前的贵的时候，出栈，并将其价格加上
                if(prices[j] > prices_j){
                    stack.removeLast();
                    res[j] += prices_j;

                }else{
                    break;
                }
            }
            //第一轮遍历时候，允许压栈，第二轮的时候只允许比较
            if(i < n){
                stack.add(i);
            }
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int num : res) {
            stringJoiner.add(num+"");
        }
        System.out.println(stringJoiner);
    }
}
