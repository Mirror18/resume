package com.mirror.石头剪刀布游戏;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Character, ArrayList<String>> map = new HashMap<>();

        while (scanner.hasNext()) {
            //获取输入，玩家名称
            String player = scanner.next();
            //猜拳的类型
            char gesture = scanner.next().charAt(0);

            //如果是非法输入
            if (gesture < 'A' || gesture > 'C') {
                System.out.println("null");
                return;
            }
            //统计各个手势的人
            map.putIfAbsent(gesture, new ArrayList<>());
            map.get(gesture).add(player);
        }

        switch (map.size()) {
            case 1:
            case 3:
                //只有一种手势或者三种手势，平局
                System.out.println("null");
                break;
            //两种手势才有结果
            case 2:
                ArrayList<String> ans;
                if (!map.containsKey('A')) {
                    ans = map.get('B');
                } else if (!map.containsKey('B')) {
                    ans = map.get('C');
                } else {
                    ans = map.get('A');
                }

                ans.sort(String::compareTo);
                ans.forEach(System.out::println);
                break;

        }
    }
}
