package com.mirror.表达式括号匹配;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //获取输入
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //进行输出和处理
        System.out.println(getResult(s));
    }

    private static int getResult(String s) {
        //入栈次数
        int count = 0;
        //创栈，stack已经被弃用，所以 自己创造
        LinkedList<Character> stack = new LinkedList<>();
        //循环处理所有字符
        for (int i = 0; i < s.length(); i++) {
            //得到字符
            char c = s.charAt(i);
            //排除无用字符
            if (c != ')' && c != '(')
                continue;
            //出栈操作
            if (stack.size() > 0 && c == ')') {
                if (stack.getLast() == '(') {
                    stack.removeLast();
                    count++;
                    continue;
                }
                return -1;
            }
            //入站
            stack.add(c);
        }
        if (stack.size() > 0)
            return -1;
        return count;
    }
}
