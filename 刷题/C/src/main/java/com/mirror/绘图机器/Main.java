package com.mirror.绘图机器;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();

        //记录答案
        long ans = 0;
        //记录上一次的坐标
        long last_x = 0;
        long last_y= 0;
        for (int i = 0; i < n; i++) {
            int cur_x = scanner.nextInt();
            int offset_y = scanner.nextInt();
            //每次都是在拐点处记录的，所以面积是这样算的
            ans += (cur_x - last_x) * Math.abs(last_y);
            last_x += cur_x;
            last_y += offset_y;
        }

        if(e > last_x){
            ans += (e - last_x) * Math.abs(last_y);
        }
        System.out.println(ans);
    }
}
