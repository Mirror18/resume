package com.mirror.围棋的气;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //首先是获取到黑白棋的位置
        int[] black = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] white = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //创造一个棋盘
        int[][] board = new int[19][19];
        //摆放棋子到棋盘上，黑棋为1，白旗为2
        for (int i = 0; i < black.length; i+=2) {
            int x = black[i];
            int y = black[i+1];
            board[x][y] = 1;
        }

        for (int i = 0; i < white.length; i+=2) {
            int x = white[i];
            int y = white[i+1];
            board[x][y] = 2;
        }
        //黑白棋的气的数量
        int black_liberty_count = 0;

        int white_liberty_count = 0;
        //偏移位置，四个方向的
        int[][] offsets = {{-1,0}, {1,0},{0,-1},{0,1}};

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(board[i][j] == 0){
                    boolean isBlackLiberty = false;

                    boolean isWhiteLiberty = false;
                    //这是对棋盘的循环，对于人来说是数棋盘上棋子周围的气
                    //对于电脑来说是一个气附近有 棋子那么这个气就属于哪个棋子的
                    //所以不会造成气的重复
                    //如果是气可以重复，那么就判断当前位置是否有棋子
                    //然后判断四周有棋子存在，不存在就气+1；
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        //这是针对边角进行处理的，因为是无效位置，所以跳过即可
                        if(newI < 0 || newI >= 19 || newJ <0 || newJ >= 19){
                            continue;
                        }

                        isBlackLiberty = isBlackLiberty || (board[newI][newJ] == 1);

                        isWhiteLiberty = isWhiteLiberty ||(board[newI][newJ] == 2);
                    }

                    if(isBlackLiberty){
                        black_liberty_count++;
                    }
                    if(isWhiteLiberty){
                        white_liberty_count++;
                    }
                }
            }
        }
        System.out.println(black_liberty_count + " " + white_liberty_count);
    }
}
