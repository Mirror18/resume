package com.mirror.素数之积;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(getResult(scanner.nextInt()));
    }

    private static String getResult(int n) {
        if (isPrime(n)) {
            return "-1 -1";
        }

        for (int i = 2; i < n; i++) {

            if (n % i == 0) {
                int j = n / i;

                if (isPrime(i) && isPrime(j)) {
                    return i < j ? i + " " + j : j + "  " + i;
                }else{
                    break;
                }

            }
        }

        return "-1 -1";
    }

    private static boolean isPrime(int n) {
        //2和3是素数
        if (n <= 3) {
            return n > 1;
        }
        //5是素数，所以这个可以包含在内
        //大于等于5的素数一定和6的倍数相邻
        if (n % 6 != 1 && n % 6 != 5) {
            return false;
        }
        //一个技巧，首先经过上面的筛选，到达这里的就是
        // 6x +1 或者 6 x - 1的数
        //统一转为加号为 5 + 6x 和 7 + 6 x
        //因为 5 和 7 统一是素数，只有1 和 本身整除
        //所以 为了保证素数能整除，则x 一定是要为 5 和 7 的倍数
        //以上解决为什么 i从 5 开始，并且判断条件为 对 5 和 7 进行取余，看能否整除
        // 那么为什么自增为 6
        //则还是对表达式的优化， 因为到达这里必定满足 6x -1 或者6x + 1.
        //对其因式分解，则需要保证余留的自然数是可以被整除掉的，又因为不能为1
        //所以对递推关系式每次取出6 + 5 上来进行
        //所以这是 +6 的原因。
        //另外根据平方的关系，所以到这里的一般是从29 出发的素数
        for (int i = 5; i <= Math.sqrt(n); i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}
