package com.mirror.攀登者;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] heights = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(heights));
    }

    private static int getResult(int[] heights) {

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            int leftHeight = i- 1>= 0 ? heights[i-1] : 0;

            int rightHeight = i + 1 < heights.length ? heights[i+1] : 0;

            if(heights[i] >leftHeight && heights[i] > rightHeight){
                count++;
            }
        }
        return  count;
    }
}
