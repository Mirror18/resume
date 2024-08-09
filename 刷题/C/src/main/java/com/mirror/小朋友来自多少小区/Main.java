package com.mirror.小朋友来自多少小区;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(getResult(nums));
        } catch (Exception e) {
            System.out.println("0");
        }
    }

    private static int getResult(int[] nums) {
        //用于去除重复的输入，value 则是出现几次
        HashMap<Integer, Integer> cnts = new HashMap<>();

        for (int num : nums) {
            //放入，value 则是从1 开始，如果查询到 +1
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }
        //有多少小朋友
        int ans = 0;
        for (int key : cnts.keySet()) {
            //这个小区有多少个小朋友，+1是要把自己算上去
            int total = key +1;
            //至于这一步为什么这么复杂，原因在于
            //当key > value + 1 怎么处理
            //当有y + 1 个小朋友 声明 y个小朋友和自己一个小区，那么可以将他们放到一个小区内
            //那么剩下的就要分段
            //这里key 是 total
            // value是 cnts.get(key).那么此时是向上取整，
            ans += Math.ceil(cnts.get(key) * 1.0 / total) * total;
        }
        //这里答案返回的是最少有多少小朋友
        //那么最多就是key +1 * value
        //来自几个小区就是Math.ceil(cnts.get(key) * 1.0 / total)
        return ans;
    }
}
