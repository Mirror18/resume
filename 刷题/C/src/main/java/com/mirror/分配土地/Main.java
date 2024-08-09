package com.mirror.分配土地;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取输入长宽
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        HashMap<Integer, Rect> rects = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = scanner.nextInt();

                //对插有旗子的进行判断，并放入num，也就是标识
                if (num > 0) {
                    rects.putIfAbsent(num, new Rect());
                    rects.get(num).setRow(i);
                    rects.get(num).setCol(j);
                }
            }
        }
        int maxArea = 0;
        for (int num : rects.keySet()) {
            Rect rect = rects.get(num);
            //计算面积
            maxArea = Math.max(maxArea, (rect.maxRow - rect.minRow + 1) * (rect.maxCol - rect.minCol + 1));
        }
        System.out.println(maxArea);
    }

    private static class Rect {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        public void setCol(int col) {
            this.minCol = Math.min(this.minCol, col);
            this.maxCol = Math.max(this.maxCol, col);
        }

        public void setRow(int row) {
            this.minRow = Math.min(this.minRow, row);
            this.maxRow = Math.max(this.maxRow, row);
        }
    }
}
