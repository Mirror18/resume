package com.mirror.叠积木;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[] nums = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(nums));;
    }

    private static int getResult(Integer[] nums) {
        int n = nums.length;

        if(n ==1){
            return  1;
        }
        if( n== 2){
            return nums[0] -nums[1] != 0 ?1:2;
        }

        Arrays.sort(nums,(a,b) -> b -a);;

        int minLen = nums[0];
        int maxLen = nums[0] + nums[nums.length-1];
        //遍历所有可能的每层长度
        for (int len = minLen;len <= maxLen; len++){
            int height = 0;

            int l = 0;
            int r = n -1;
            //如果有长度等于每层长度，则就可以单独作为一层
            while( l < n && nums[l] == len){
                l++;
                height++;
            }
            //这里是将所有可以两个作为一层的进行合并
            while( l < r){
                if(nums[l] + nums[r] != len){
                    break;
                }
                l++;
                r--;
                height++;
            }
            //有两个和一个都无法处理的，就继续下一个循环‘
            //下一个每层长度
            //最后返回 -1；
            if( l <= r){
                continue;
            }
            return  height;
        }
        return  -1;
    }
}
