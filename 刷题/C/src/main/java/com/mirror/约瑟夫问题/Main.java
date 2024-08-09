package com.mirror.约瑟夫问题;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[] arr = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        int len = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println(getResult(arr,len,m));

    }

    private static String getResult(Integer[] arr, int len, int m) {

        LinkedList<Integer> dq = new LinkedList<>(Arrays.asList(arr));

        LinkedList<Integer> output_arr = new LinkedList<>();

        int i = 1;
        while(len > 0){
            Integer out = dq.removeFirst();

            if(i == m){
                output_arr.add(out);
                m = out;
                i = 1;
                len --;
            }else{
                dq.add(out);
                i++;
            }
        }

        StringJoiner stringJoiner = new StringJoiner(",");
        for (Integer ele : output_arr) {
            stringJoiner.add(ele+"");
        }
        return stringJoiner.toString();
    }
}
