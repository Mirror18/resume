package com.mirror.爱吃蟠桃的悟空;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //接受输入
        Scanner scanner = new Scanner(System.in);
        //通过stream流接受一整行，并按照空格划分，最后转换为int，然后转换为数组
        int[] cnts  = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       //接受最后一个数据
        int h = Integer.parseInt(scanner.nextLine());
//处理数据并输出
        System.out.println(getResult(cnts,h));
    }

    private static int getResult(int[] cnts, int h) {
        //如果桃树的数量＞回来的时间，表示为不可能
        if(cnts.length>h){
            return 0;
        }
//表示最大吃完时间
        int max = Arrays.stream(cnts).max().orElse(0);
//如果刚好想等就是最大时间
        if(cnts.length == h){
            return max;
        }
//通过二分查找来判断最优解
        int min = 1;

        int ans = max;

        while(min <= max){
            //除以二
            int mid = (min+max) >> 1;
//检查是否成立
            if(check(mid,h,cnts)){
                ans = mid;
                max = mid -1;
            }else{
                min = mid + 1;
            }
        }
        return ans;
    }
//如何检查时间是否成立
    private static boolean check(int speed, int limit, int[] cnts) {
        int cost = 0;
        //通过调用数组内容
        for (int cnt : cnts) {
            //将每一行的数值进行判断。
            //花费的时间按照每科桃树的桃子数量进行计算花费时间
            cost += cnt/ speed +(cnt % speed > 0 ? 1: 0);
            if(cost > limit){
                return false;
            }
        }
        return true;
    }
}
