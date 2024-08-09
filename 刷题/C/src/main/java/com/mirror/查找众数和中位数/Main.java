package com.mirror.查找众数和中位数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(a));
    }

    private static int getResult(int[] nums) {
        //用哈西表存储各个元素的次数。实话说这也是为什么hashset底层实现是用hashmap
        HashMap<Integer,Integer> count = new HashMap<>();
        //通过调用hashmap来存储出现的次数
        for (int num : nums) {
            count.put(num,count.getOrDefault(num,0) + 1);
        }
        //比较出最大的次数
        int max = count.values().stream().max((a,b) -> a -b).orElse(0);

        //最后的输出用动态数组存储
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer k : count.keySet()) {
                if(count.get(k)  == max){
                    ans.add(k);
                }
        }
        //升序排序
        ans.sort((a,b) -> a -b);
        //查找中位数
        int mid = ans.size() /2;
        if(ans.size() %2 == 0){
            return (ans.get(mid) + ans.get(mid - 1)) /2;
        }else{
            return ans.get(mid);
        }
    }
}
