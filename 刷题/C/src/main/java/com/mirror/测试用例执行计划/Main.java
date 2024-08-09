package com.mirror.测试用例执行计划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入一整行，并获取值
        int[] a1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = a1[0];
        int m = a1[1];
        //获取每一行的值
        int a2[] = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            a2[i] = Integer.parseInt(scanner.nextLine());
        }
        //创建一个数据结构的动态数组
        ArrayList<caseTest> ans= new ArrayList<>();
        for (int i = 1; i <= m ; i++) {
            //处理输入数据
            int priority =
                    Arrays.stream(scanner.nextLine().split(" "))
                            .map(Integer::parseInt)
                            .map(id->a2[id])
                            .reduce(Integer::sum)
                            .orElse(0);
            ans.add(new caseTest(i,priority));
        }
        //动态数组进行排序
        ans.stream()
                .sorted((a,b) -> a.property != b.property ? b.property -a.property:a.id-b.id)
                .map(caseTest -> caseTest.id)
                .forEach(System.out::println);
    }
    private static class caseTest{
        int id;
        int property;

        public caseTest(int id, int property){
            this.id = id;
            this.property = property;
        }
    }
}