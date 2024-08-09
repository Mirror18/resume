package com.mirror.符号运算;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static class Fractions {
        int fa;
        int ch;

        public Fractions(int fa, int ch) {
            this.fa = fa;
            this.ch = ch;
        }

        public Fractions() {
        }
    }

    //操作数
    static LinkedList<Fractions> oper_num = new LinkedList<>();
    //操作符号栈
    static LinkedList<Character> oper_sign = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        //运算符号优先级
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        //数字缓存
        StringBuilder numStr = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            //当处理数字时候，将所有连续的数字组合到一起
            if (c >= '0' && c <= '9') {
                while (c >= '0' && c <= '9') {
                    numStr.append(c);
                    if (i + 1 >= s.length()) {
                        break;
                    }
                    i++;
                    c = s.charAt(i);
                }
                //清除缓存，并放到操作数中
                oper_num.addLast(new Fractions(1, Integer.parseInt(numStr.toString())));
                numStr = new StringBuilder();
            }
            //如果是处理操作符号
            //如果上一个优先级比当前高，就先计算
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (oper_sign.size() > 0
                        && oper_sign.getLast() != '('
                        && priority.get(c) <= priority.get(oper_sign.getLast())) {
                    calc();
                }
                oper_sign.addLast(c);
            } else if (c == ')') {
                //当读取到括号的是时候，就把括号内进行计算
                while (oper_sign.getLast() != '(') {
                    calc();
                }
                oper_sign.removeLast();
            } else if (c == '(') {
                oper_sign.add(c);
            }
            i++;
        }
        //当操作数有多个的时候，进行最后的收尾
        //前面只是读取的时候顺便计算
        while (oper_num.size() > 1) {
            calc();
        }
        //这是最后的结果
        Fractions result = oper_num.removeLast();

        if (result.fa == 0) {
            return "ERROR";
        }
        //得到最大的公因子
        int k = getMaxCommonDivisor(result.fa, result.ch);

        result.fa /= k;
        result.ch /= k;

        String sign = result.fa * result.ch < 0 ? "-" : "";

        int fa = Math.abs(result.fa);
        int ch = Math.abs(result.ch);

        if (fa == 1) {
            return sign + ch;
        } else {
            return sign + ch + "/" + fa;
        }
    }


    private static int getMaxCommonDivisor(int fa, int ch) {
        while (ch != 0) {
            int tmp = ch;
            ch = fa % ch;
            fa = tmp;
        }
        return fa;
    }

    private static void calc() {

        Fractions b = oper_num.removeLast();
        Fractions a = oper_num.removeLast();

        char op = oper_sign.removeLast();

        Fractions result = new Fractions();
        switch (op) {
            case '+':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.fa + b.ch * a.fa;
                break;
            case '-':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.fa - b.ch * a.fa;
                break;
            case '*':
                result.fa = a.fa * b.fa;
                result.ch = a.ch * b.ch;
                break;
            case '/':
                result.fa = a.fa * b.ch;
                result.ch = a.ch * b.fa;
                break;
        }
        oper_num.add(result);
    }
}

