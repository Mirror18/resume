package com.mirror.精准核酸检测;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //接受数据中
//        int n = Integer.parseInt(scanner.nextLine());
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] confirmed = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(",")) .mapToInt(Integer::parseInt).toArray();
        }
        //处理并打印结果
        System.out.println(getResult(n,confirmed,matrix));

    }

    private static int getResult(int n, int[] confirmed, int[][] matrix) {
        //首先是创建一个并查集
        UnionFindSet ufs = new UnionFindSet(n);

        //根据结果放入并查集之间的关系
        //这是上三角形的查询
        for (int i = 0; i < n; i++) {
            for (int j = i; j <n ; j++) {
                if(matrix[i][j] == 1){
                    ufs.union(i,j);
                }

            }
        }

        //统计每个接触群体中的人数
        int[] cnts = new int[n];
        for (int i = 0; i < n; i++) {
            //通过查询是属于哪个组的，将所有人数进行一个统计
            int fa = ufs.find(i);
            cnts[fa]++;
        }
        //记录已经统计过的感染人数
        HashSet<Integer> confirmed_fa = new HashSet<>();

        int ans = 0;
        //这里是已经感染的人群
        for (int i : confirmed) {
            int fa = ufs.find(i);
            //这的逻辑是添加每个传染者传播的源头，
            //这里一个跳出的逻辑是为了防止多个感染者同时感染一个，造成数据错误
            //这个感染群体已经统计过，就不在统计
            if(confirmed_fa.contains(fa)){
                continue;
            }
            confirmed_fa.add(fa);

            ans += cnts[fa];
        }
        //最终人数不包括已经确定的
        return  ans - confirmed.length;
    }
}

class UnionFindSet{
    int[] fa;

    public UnionFindSet(int n){
        this.fa = new int[n];
        for (int i = 0; i < n ; i++) {
            fa[i] = i;
        }
    }

    public int find(int x){
        if(x != this.fa[x]){
            this.fa[x] = this.fa[this.fa[x]];
            return this.fa[x];
        }
        return  x;
    }

    public void union(int x, int y){
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if(x_fa != y_fa){
            this.fa[y_fa] = x_fa;
        }
    }
}
