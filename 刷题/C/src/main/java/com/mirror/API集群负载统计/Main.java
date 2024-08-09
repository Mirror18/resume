package com.mirror.API集群负载统计;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 访问历史日志的条数
        //处理数据的条数
        int n = sc.nextInt();

        // 记录各层级上各关键字出现的频次
        //设计结构存储数据处理样式。动态数组的长度是最大层数。
        //hashmap是存储当前层级的所有关键字，和出现次数
        ArrayList<HashMap<String, Integer>> cnts = new ArrayList<>();

        // 遍历历史日志
        for (int i = 0; i < n; i++) {
            // 将日志按照 "/" 分割，注意split函数会将 "/a/b/c" 会分割出数组 ["", "a", "b", "c"]，因此a,b,c的层级就是其索引值
            //处理传入信息，分层
            String[] urlComponents = sc.next().split("/");

            // 遍历url的各层级
            //遍历层级处理层级信息
            for (int level = 0; level < urlComponents.length; level++) {
                String urlComponent = urlComponents[level];

                // 如果cnts不存在对于层级
                //依次添加层数。放到外部也需要循环添加
                if (cnts.size() <= level) {
                    // 则创建对应层级
                    cnts.add(new HashMap<>());
                }

                // 获取对应层级
                HashMap<String, Integer> map = cnts.get(level);
                // 在对应层级上将对应关键字出现次数+1
                map.put(urlComponent, map.getOrDefault(urlComponent, 0) + 1);
            }
        }

        // 要查询的给定层级
        int tar_level = sc.nextInt();
        // 要查询的关键字
        String tar_urlComponent = sc.next();

        if (tar_level >= cnts.size()) {
            // 如果要查询的层级超出了统计范围，则返回0
            System.out.println(0);
        } else {
            // 获取对应层级上对应关键字出现的频次，如果没有出现，则返回0
            System.out.println(cnts.get(tar_level).getOrDefault(tar_urlComponent, 0));
        }
    }
}