package com.mirror.找座位;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] arr = scanner.nextLine().toCharArray();

        System.out.println(getResult(arr));
    }

    private static int getResult(char[] arr) {
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                boolean isLeftEmpty = i == 0 || arr[i - 1] == '0';
                boolean isRightEmpty = i == arr.length - 1 || arr[i + 1] == '0';

                if (isLeftEmpty && isRightEmpty) {
                    ans++;
                    arr[i] = '1';
                    i++;
                }
            }
        }

        return ans;
    }
}
