package com.mirror.出错的或电路;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String bin1 = scanner.next();
        String bin2 = scanner.next();

        System.out.println(getResult(n,bin1,bin2));
    }

    private static long getResult(int n, String bin1, String bin2) {
        //找bin2 值为 0 的位置，对应bin1的值为0 的个数
        long x = 0;
        //对应值为1 的个数
        long y = 0;
        //统计bin1 总共有多少1
        long a = 0;
        //统计 bin2 总共有多少个 0
        long b = 0;

        for (int i = 0; i < n; i++) {
            if(bin1.charAt(i) == '0'){
                b++;
                if(bin2.charAt(i) == '0') {
                    x++;
                }
            }else{
                a++;
                if(bin2.charAt(i) == '0') {
                    y++;
                }
            }
        }
        return x * a + y * b - x * y;
    }
}
