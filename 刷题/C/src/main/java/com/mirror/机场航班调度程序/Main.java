package com.mirror.机场航班调度程序;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] flights = scanner.nextLine().split(",");
        StringJoiner stringJoiner = new StringJoiner(",");
        Arrays.stream(flights)
                .sorted((a,b) -> {
                    String abbr1 = a.substring(0,2);
                    String num1 = a.substring(2);

                    String abbr2 = b.substring(0,2);
                    String num2 = b.substring(2);

                    if (abbr1.equals(abbr2)) {
                        return  num1.compareTo(num2);
                    }else{
                        return abbr1.compareTo(abbr2);
                    }

                })
                .forEach(stringJoiner::add);

        System.out.println(stringJoiner);
    }
}
