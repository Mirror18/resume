package com.mirror.手机APP防沉迷系统;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class App {
        String name;
        int priority;
        int startTime;
        int endTime;

        public App(String name, int priority, int startTime, int endTime) {
            this.endTime = endTime;
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
        }
    }

    public static int convert(String time) {
        String[] tmp = time.split(":");

        String hours = tmp[0];
        String minutes = tmp[1];
        return Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入总个数
        int n = scanner.nextInt();
        //这里选用动态数组存储，其实用APP数组也成，需要注册的APP
        ArrayList<App> apps = new ArrayList<>();
        //进行添加
        for (int i = 0; i < n; i++) {
            apps.add(new App(scanner.next(), scanner.nextInt(), convert(scanner.next()), convert(scanner.next())));
        }
        //输入时间段，因为不需要搞特殊存储结构，所以用来计算时间的方法来转换字符
        //需要查询的时间点
        int queryTime = convert(scanner.next());

        System.out.println(getResult(apps,queryTime));
    }

    private static String getResult(ArrayList<App> apps, int queryTime) {
        //记录已经注册的APP
        ArrayList<App>  registereds = new ArrayList<>();

        outer:
        for (App app : apps) {
            //如果是不符合的时间段则略过，无法注册
            if(app.startTime >= app.endTime){
                continue ;
            }
            //记录已经注册的APP中被注销的APP的位置
            ArrayList<Integer> idxs = new ArrayList<>();
            //对每个时间段进行处理，这里就是已经注册的APP和需要注册的APP进行比较
            for (int i = 0; i < registereds.size(); i++) {
                //已经注册的APP取出来
                App registered = registereds.get(i);
                //不符合的时间段就跳过
                //已经注册的和现在需要注册的APP符合就跳过
                if(registered.startTime >= app.endTime || app.startTime >= registered.endTime){
                    continue ;
                }
                //如果当前需要注册的APP优先级大于已经注册的APP
                //则进行添加在已经注册的APP中需要删除的APP地址
                //但是小于 已经注册的优先级，则不进行注册，直接跳过
                if(app.priority > registered.priority){
                    idxs.add(i);
                }else{
                    continue outer;
                }
            }
            //因为已经注册的APP需要删除的可能不是一个，所以进行删除已经注册的时间端
            //这里倒序则是进行了优化
            for (int i = idxs.size() -1; i >=0 ; i--) {
                int deleteIdx = idxs.get(i);
                registereds.remove(deleteIdx);
            }
            //需要注册中已经注册的APP列表
            registereds.add(app);
        }
        String ans = "NA";

        //通过在已经注册的APP中进行查询，
        //如果已经找到则就进行返回
        for (App app : registereds) {
            if(queryTime >= app.startTime && queryTime <app.endTime){
                ans = app.name;
                break;
            }
        }
        return ans;
    }
}
