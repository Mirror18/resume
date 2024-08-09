package com.mirror.剩余银饰重量;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        LinkedList<Integer> weights = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            weights.add(scanner.nextInt());
        }

        weights.sort( (a,b) -> a -b);

        while(weights.size() >= 3){
            int  z = weights.removeLast();
            int  y = weights.removeLast();
            int  x = weights.removeLast();

            int remain = Math.abs((z - y) - ( y -x));

            if(remain != 0){
                int idx = Collections.binarySearch(weights,remain);

                if(idx < 0){
                    idx = - idx -1;
                }

                weights.add(idx, remain);
            }
        }

        if(weights.size() == 2){
            System.out.println(Math.max(weights.get(0), weights.get(1)));
        }else if( weights.size() == 1){
            System.out.println(weights.get(0));
        }else{
            System.out.println(0);
        }
    }
}
