package com.mirror.内存冷热标记;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //读取输入
        int n = scanner.nextInt();
        //使用hashmap记录每个整数和出现的次数
        HashMap<Integer, Integer> cnts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            //遍历n个整数，每个整数出现的次数存储。如果整数已经存在，则出现次数+1，不存在则初始化为1
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }

        int threshold = scanner.nextInt();
        //过滤，过滤掉value＜ threshold的整数
        cnts.keySet().removeIf(num -> cnts.get(num) < threshold);
        System.out.println(cnts.size());

        cnts.entrySet().stream()
                .sorted(
                        (a, b) ->
                                a.getValue() - b.getValue() != 0
                                        ? b.getValue() - a.getValue()
                                        : a.getKey() - b.getKey()
                )
                .forEach(a -> System.out.println(a.getKey()));
    }
}
