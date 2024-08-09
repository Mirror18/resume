package com.mirror.提取字符串中的最长合法简单数学表达式;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Pattern p = Pattern.compile("((\\d+[+*-])*\\d+)");

    public static void main(String[] args) {
        //怎么说呢，获取输入
        Scanner scanner = new Scanner(System.in);

        System.out.println(getResult(scanner.nextLine()));
    }

    private static long getResult(String s) {
        //首先是提取出表达式，并转换为字符串
        String maxLenExp = getMaxLenExp(s);
        //如果没有表达式就返回0
        if (maxLenExp.length() == 0) {
            return 0;
        } else {
            //如果表达式正规就计算表达式内容
            return calcExpStr(maxLenExp);
        }
    }

    /**
     * 这是根据字符串进行计算表达式
     * @param exp
     * @return
     */
    private static long calcExpStr(String exp) {
        //那么问题就回到了这里，为什么末尾添加+0的操作
        //原因很简单，当不添加一个+0的时候，那么进行到最后是什么
        //numStr中缓存最后一个数字，没有压入栈中计算
        //所以是为了最后一步的压栈准备的
        exp += "+0";
        //记录操作数的
        LinkedList<Long> stack = new LinkedList<>();
        //各个操作数的值部分的缓存
        StringBuilder numStr = new StringBuilder();
        //操作数洗漱部分
        long num_coef = 1;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            //如果当前字符为数字
            if (c >= '0' && c <= '9') {
                //先添加到缓存中
                numStr.append(c);
                continue;
            }
            //到达这一步的时候，c存放的是运算符号
            //所以先将字符串转换为数字
            long num = num_coef * Long.parseLong(numStr.toString());
            //栈内压入数字
            stack.add(num);
            //清理缓存
            numStr = new StringBuilder();
            //匹配当前的操作符号
            switch (c) {
                case '+':
                    num_coef = 1;
                    break;
                case '-':
                    //将其转换为负数
                    num_coef = -1;
                    break;
                case '*':
                    num_coef = stack.removeLast();
                    break;
            }
        }
        //表达式分块后，每一块都是独立计算的，所有块的和就是表达式的结果
        long res = 0;
        for (long num : stack) {
            res += num;
        }
        return res;
    }

    /**
     * 获取表达式
     * 首要做的就是根据正则表达式提取出表达式
     * 然后返回
     * @param s
     * @return
     */
    private static String getMaxLenExp(String s) {
        Matcher matcher = p.matcher(s);
        //要求是提取出最长合法表达式，所以有了这一步
        String maxLenExp = "";

        while (matcher.find()) {
            //这是匹配到的内容
            String exp = matcher.group(0);
            //如果表达式长度大于已经存在的表达式就更新
            if (exp.length() > maxLenExp.length()) {
                maxLenExp = exp;
            }
        }
        //返回最大的表达式
        return maxLenExp;
    }
}
