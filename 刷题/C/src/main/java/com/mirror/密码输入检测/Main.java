package com.mirror.密码输入检测;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c=='<'){
                if(stack.isEmpty()){
                    continue;
                }
                stack.removeLast();
            }else{
                stack.addLast(c);
            }
        }
        int upper = 0;
        int lower = 0;
        int number =0;
        int non_letter_number = 0;
        StringBuilder password = new StringBuilder();
        for (Character c : stack) {
            password.append(c);

            if (c >= 'a' && c <= 'z') {
                lower++;
            } else if (c >= 'A' && c <= 'Z') {
                upper++;
            } else if (c >= '0' && c <= '9') {
                number++;
            } else {
                non_letter_number++;
            }
        }

        if (password.length() >= 8
                && lower >= 1
                && upper >= 1
                && number >= 1
                && non_letter_number >= 1) {
            password.append(",true");
        } else {
            password.append(",false");
        }

        System.out.println(password);
    }
}
