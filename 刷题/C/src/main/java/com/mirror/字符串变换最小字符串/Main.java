package com.mirror.字符串变换最小字符串;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        //这里得到最小字符串，绝对意义上，因为只能交换一次，所以需要判定
        char[] minArr = s.toCharArray();
        Arrays.sort(minArr);
        //这是字符串
        String minS = new String(minArr);
        if(minS.equals(s)){
            return s;
        }
        //处理的数组
        char[] sArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            //当字符数组和最小字符数组不同的时候
            if(sArr[i] != minArr[i]){
                //暂存
                char tmp = sArr[i];
                //要交换的字符
                sArr[i] = minArr[i];
                //最后一个
                int swapIndex =s.lastIndexOf(minArr[i]);
                sArr[swapIndex] = tmp;
                break;
            }
        }
        return new String(sArr);
    }
}
