package com.mirror.靠谱的车;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //这是将数字按照位数处理
        int[] arr = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(arr));
    }

    private static int getResult(int[] arr) {
        //最终的结果
        int correct =0;
        //循环处理每一位数
        for (int i = 0; i < arr.length; i++) {
            //记录当前位数的数字
            int fault = arr[i];
            //判断当前位数是否＞4
            if(fault > 4){
                fault --;
            }
            //这里是跳过3 和 8
//            if(fault > 8){
//                fault  = fault -2;
//            }else if( fault > 3){
//                fault --;
//            }
            //因为是从最高位处理，所以要判断当前要换成多少
            for (int j = arr.length - i - 1; j > 0; j--) {
                fault *= 9;
//                fault *= 8;
            }
            //最后的结果进行累计
            correct += fault;
        }
        return  correct;
    }
}
