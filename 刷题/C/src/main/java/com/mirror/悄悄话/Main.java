package com.mirror.悄悄话;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] times = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = 0;
        //用链表创建一个二叉树，本质上只是提供一个层序遍历的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //首先是初始化，不然第一个数据需要做特殊处理
        queue.addLast(0);

        while(queue.size()>0){
            int fa = queue.removeFirst();
            //左节点和右节点
            int ch1 = 2 * fa + 1;
            int ch2 = 2 * fa + 2;
            //判断左右节点是否存在
            boolean ch1_exist = ch1 < times.length && times[ch1] != -1;
            boolean ch2_exist = ch1 < times.length && times[ch2] != -1;
            //左右节点存在只是为了确保
            //1. 可以做到层序遍历的顺序
            if(ch1_exist){
                times[ch1] += times[fa];
                queue.addLast(ch1);
            }

            if(ch2_exist){
                times[ch2] += times[fa];
                queue.addLast(ch2);
            }

            if(!ch1_exist && !ch2_exist){
                ans = Math.max(ans, times[fa]);
            }

        }
        System.out.println(ans);
    }
}
