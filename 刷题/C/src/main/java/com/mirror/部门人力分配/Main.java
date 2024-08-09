package com.mirror.部门人力分配;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //这里获取方法不一致是因为会留下一个换行，出现错误。所以这里读取一行
        int m = Integer.parseInt(scanner.nextLine());
        //stream的API方法获取到值
        int[] requirements = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(m,requirements));
    }

    private static long getResult(int m, int[] requirements) {
        //对获取到的数组进行排序
        Arrays.sort(requirements);
//获取到数组长度，并获取到数组中最大的值和最大的前两个值
        int n = requirements.length;
        long  min = requirements[n -1];
        long max = requirements[n -1]+ requirements[n-2];
//暂定结果试试最大值
        long ans = max;
        //二分查找中，
        while(min <= max){
            long mid = (min + max) >> 1;
            if(check(mid, m , requirements)){
                ans = mid;
                max = mid - 1;
            }
            else{
                min = mid + 1;
            }
        }
        return ans;
    }
//检查的逻辑是在数组中尝试
    private static boolean check(long limit, int m, int[] requirements) {
        int l = 0;
        int r = requirements.length - 1;
        int need = 0;
        //当最大加最小能满足限制时候，两边同时缩小，need++
        //不能满足，那么最大的只能单独一个，所以就need++,r--.
        //
        while(l<=r){
            if(requirements[l] + requirements[r] <= limit){
                l++;
            }
            r--;
            need++;
        }
        return m >= need;
    }
}
