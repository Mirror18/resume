package com.mirror.求字符串中所有整数的最小和;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(getResult(scanner.nextLine()));
    }

    private static String getResult(String s) {
        //这个容器是用来辨别是否为负号，表示是否在处理一个负数
        boolean isNegative = false;
        //这是新组成的数字，用来临时存储数字部分，负数数字部分
        StringBuilder negative = new StringBuilder();
        //这是最后的结果，用来处理极大数
        BigInteger ans = new BigInteger("0");
        //通过循环查询字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果是数字的话
            if (c >= '0' && c <= '9') {
                //如果有负号则就继续添加
                if (isNegative) {
                    negative.append(c);
                } else {
                    //正数则就使用加法计算最终结果
                    ans = ans.add(new BigInteger(c + ""));
                }
            } else {
                //如果是有负号，并且负数组成不为空
                if (isNegative && negative.length() > 0) {
                    //则就进行想减
                    ans = ans.subtract(new BigInteger(negative.toString()));
                    //并重新生成一个负数部分
                    negative = new StringBuilder();
                }
                //负数判别符号
                isNegative = c == '-';
            }
        }
        //这是处理末尾的负数部分
        if (negative.length() > 0) {
            ans = ans.subtract(new BigInteger(negative.toString()));
        }
        return ans.toString();

    }
}
