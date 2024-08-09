package com.mirror.掌握的单词个数;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.next();
        }

        String chars = scanner.next();

        System.out.println(getResult(words, n, chars));
    }

    private static int getResult(String[] words, int n, String chars) {
        int ans = 0;

        int[] cnt_chars = charStatistic(chars);

        for (int i = 0; i < n; i++) {
            int diff = 0;

            int[] cnt_word = charStatistic(words[i]);
            //对每个字符和单词进行比较出现的次数，并将缺少的放入到diff中
            for (int j = 0; j < 128; j++) {
                diff += Math.max(cnt_word[j] - cnt_chars[j], 0);
            }
            //如果缺少的字符符合万能字符的数量，则可以完成
            if (diff <= cnt_chars['?']) {
                ans++;
            }
        }
        return ans;
    }

    //统计对应字符出现的次数
    private static int[] charStatistic(String chars) {
        int[] cnts = new int[128];

        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            cnts[c] += 1;
        }
        return cnts;
    }

}
