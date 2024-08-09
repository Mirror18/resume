package com.mirror.分割均衡字符串;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int countX = 0;
        int countY = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'X'){
                countX++;
            }else{
                countY++;
            }
            if(countX == countY){
                count++;
            }
        }
        System.out.println(count);
    }
}
