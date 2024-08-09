package com.mirror.求满足条件的最长子串的长度;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(getResult(scanner.next()));
    }

    private static int getResult(String str) {
        //最大子串长度
        int maxLen = -1;
        //是否有字母
        boolean hasLetter = false;
        //窗口左右边界
        int l = 0, r = 0;
        //包含字母的字符串，做为一个队列用了
        //存储的是字母所在的数组下标
        LinkedList<Integer> letterIdx = new LinkedList<>();

        while (r < str.length()) {
            //获得字符
            char c = str.charAt(r);
            //如果是字母的情况下
            if (isLetter(c)) {
                //满足有字母
                hasLetter = true;
                //添加字母
                letterIdx.add(r);
                //如果是包含多个字母的情况下
                //就重新制定左边界，为第一个字母的下一个位置
                if (letterIdx.size() > 1) {
                    l = letterIdx.removeFirst() + 1;
                }
                //这种是仅有一个字母的情况
                if (r == l) {
                    r++;
                    continue;
                }
            }
            //子串长度
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        //如果子串中不存在字母
        if (!hasLetter) {
            return -1;
        }
        return maxLen;
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
