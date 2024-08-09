package com.mirror.多段线数据压缩;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        //获取到输入流
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //处理结果
        System.out.println(getResult(nums));
    }

    private static String getResult(int[] nums) {
        // 输出格式
        StringJoiner stringJoiner = new StringJoiner(" ");
        //上一个点坐标
        int preX = nums[0];
        int preY = nums[1];
        //上一次的运动方向
        int preDirectX = 0;
        int preDirectY = 0;

        for (int i = 2; i <nums.length ; i += 2) {
            //当前点坐标
            int curX = nums[i];
            int curY = nums[i +1];
            //上一个点到当前点的偏移量
            int offsetX = curX - preX;
            int offsetY = curY - preY;
            //进行单位化
            int base = Math.max(Math.abs(offsetX),Math.abs(offsetY));
            int directX = offsetX / base;
            int directY = offsetY / base;
            //如果两次运行方向不同
            if(directX != preDirectX || directY != preDirectY){
                //上一个点是拐点，需要记录下来
                stringJoiner.add(preX + " " + preY);
            }
            //进行更新
            preX = curX;
            preY = curY;
            preDirectX = directX;
            preDirectY = directY;
        }
        //记录最后一个点
        stringJoiner.add(preX + " " + preY);

        return stringJoiner.toString();
    }
}
