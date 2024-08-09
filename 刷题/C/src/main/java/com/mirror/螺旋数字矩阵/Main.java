package com.mirror.螺旋数字矩阵;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        getResult(n,m);
    }

    //这个问题似乎是写过
    //不过原理很简单，就是要判断什么时候需要转弯
    //本质上就是对二维数组的填充
    private static void getResult(int n, int m) {

        int k = (int) Math.ceil(n * 1.0 /m);

        int [][] matrix = new int[n][k];

        int step = 1;

        int x = 0;
        int y = 0;
        while(step <= n){
            //这三个就是边界条件
            while (y <k && matrix[x][y] ==0 && step<=n){
                matrix[x][y++] = step++;
            }
            y-=1;
            x+=1;
            while(x <m && matrix[x][y]==0 && step<=n){
                matrix[x++][y] = step++;
            }
            x-=1;
            y-=1;
            while(y>=0 && matrix[x][y]==0 && step<=n){
                matrix[x][y--] = step++;
            }
            y+=1;
            x-=1;

            while(x >=0 && matrix[x][y]==0 && step<=n){
                matrix[x--][y] = step++;
            }
            x+=1;
            y+=1;
        }

        for (int i = 0; i < m; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < k; j++) {
                if(matrix[i][j] == 0){
                    row.add("*");
                }else{
                    row.add(matrix[i][j] +"");
                }
            }
            System.out.println(row);
        }
    }
}
