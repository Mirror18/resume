package com.mirror.最长的指定瑕疵度的元音子串;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int flaw = scanner.nextInt();

        String s = scanner.next();

        System.out.println(getResult(flaw, s));
    }

    private static int getResult(int flaw, String s) {
        char[] yuan = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};

        HashSet<Character> set = new HashSet<>();
        for (char c : yuan) {
            set.add(c);
        }
        //添加原先字符串中的元音所在的索引
        ArrayList<Integer> idxs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                idxs.add(i);
            }
        }

        int ans = 0;
        int n = idxs.size();

        int l = 0;
        int r = 0;

        while (r < n) {
            //字符串中的长度 - 元音字符的长度
            int diff = idxs.get(r) - idxs.get(l) - (r - l);

            if(diff > flaw){
                l++;
            }else if( diff <flaw){
                r++;
            }else{
                ans = Math.max(ans,idxs.get(r) - idxs.get(l) + 1);
                r++;
            }
        }
        return ans;
    }
}
