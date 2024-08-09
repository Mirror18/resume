package com.mirror.寻找相同子串;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 实话说，这就是kmp算法，
 * 写的时候是最难写，这里我选择自己写一个
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String subStr = scanner.nextLine();

        System.out.println(getResult(str, subStr));
//        System.out.println(Arrays.toString(getNest(str)));
    }

    private static String getResult(String str, String subStr) {
        if (str.length() < subStr.length()) {
            return "No";
        }

        int idx = str.indexOf(subStr);
        if (idx == -1) {
            return "No";
        } else {
            return idx + 1 + "";
        }
    }

    public static int indexOf(String s, String t) {
        int[] next = getNest(t);
        //主串位置
        int i = 0;
        //子串位置
        int j = 0;

        while (i < s.length() && j < t.length()) {
            //正常比对
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                //如果没匹配到，就让子串回退
                //主串不动
                //j > 0 保证不是第一个子串回退
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    i++;
                }
            }
        }
        //while 循环结束的条件是 i 和 j运行完成
        //当 j到了末尾，返回正常值
        if (j == t.length()) {
            return i - j;
        } else {
            return -1;
        }

    }

    /**
     * 求解next 数组
     * <p>
     * 本质上next 数组 就是求解子串中可以做优化的地方
     * <p>
     * 那么首先要明确的是 next 数组下标代表的是什么，内容是什么
     * <p>
     * 根据上面算法的反馈，其实就是当 主串和子串发生不匹配的时候，主串不动，
     * 子串应该移动到哪里才能继续比较
     * <p>
     * 也就是说，next 数组，下标代表的是 在这个位置发生了不匹配
     * 内容是 要返回的 子串返回的地址
     * <p>
     * 所以可以看出这与主串无关系，只需要对子串进行优化即可
     * <p>
     * 优化的原理就是子串当前位置发生了不匹配，那么前面的子串作为一个整体
     * <p>
     * 看子串的子串 他的对称性
     *
     * 例如子串 ababaa
     * 他的 next 数组就为  0 0 1 2 3 1
     *
     * 那么怎么去写算法呢，首先是创建一个同样大小的数组
     * 全部赋值为 0
     * 用 k 和 j 表示前缀和后缀
     * 然后是动态数组q求解
     * 一前一后扫描，j & k 最小差值为 1
     * 也就是说首位固定为0
     *
     * 核心逻辑是什么呢，核心逻辑是通过循环找到中心点
     * 怎么说，因为当j & k内容不同的时候 j ++。也就是直到找到和i 相同的地方
     * 那么这个时候就就可以得到有多少相同的前后缀，
     *
     * 那么 怎么理解 K = next[k-1]呢，因为为我们求的就是相同前后缀有多长，当前不匹配那么自然就是上一个的
     *
     * 所以这才是核心逻辑
     * 下标代表这从 0 到这个下标值这个子串
     * value代表着 0 到这个下标值之间 前后缀相同的长度
     * @param t
     * @return
     */
    private static int[] getNest(String t) {
        //首先是创建一个空数组
        int[] next = new int[t.length()];
        //扫描后缀
        int j = 1;
        //扫描前缀
        int k = 0;
        while (j < t.length()) {
            if (t.charAt(j) == t.charAt(k)) {
                //是在 下标 1 处开始 赋值， next[0] = 0;
                //这里是kmp算法中next数组求解的精髓
                next[j] = k + 1;
                j++;
                k++;
            } else {
                if (k > 0) {
                    k = next[k - 1];
                } else {
                    j++;
                }
            }
        }
        return next;
    }
}