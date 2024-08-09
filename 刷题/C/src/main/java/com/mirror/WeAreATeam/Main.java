package com.mirror.WeAreATeam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取人群总数和消息总数
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //将消息存放到二维数组中
        int[][] msgs = new int[m][3];
        for (int i = 0; i < m; i++) {
            msgs[i][0] = scanner.nextInt();
            msgs[i][1] = scanner.nextInt();
            msgs[i][2] = scanner.nextInt();
        }

        //处理二维数组
        getResult(msgs, n, m);
    }

    private static void getResult(int[][] msgs, int n, int m) {
        //判断输入参数
        if(n <1 || n >= 10000 || m < 1 || m>= 1000){
            System.out.println("NULL");
        }
        //制作并查集，n+1 是因为n个数据有n+1种关系
        UnionFindSet ufs = new UnionFindSet(n + 1);
        //判断C位
        for (int[] msg : msgs) {
            int a = msg[0], b = msg[1], c = msg[2];
            //当C位不为标准输入时
            if (a < 1 || a > n || b < 1 || b > n){
                System.out.println("da pian zi");
                continue;
            }
            //当有关系时候
            if (c == 0){
                //放入关系
                ufs.union(a,b);
            }
            //处理查询请求
            else if(c == 1){
                System.out.println(ufs.find(a) == ufs.find(b) ? "we are a team" : "we are not a team");
            }else{
                System.out.println("da pian zi");
            }
        }
    }
    //这种并查集很有意思，我之前只学习过并查集的思想，从未看过源码
    private static class UnionFindSet {
        //用于存放关系，或者说存放和那个节点属于同一集合的
        int[] fa;
        public UnionFindSet(int n){
            //获取长度进行初始化
            this.fa = new int[n];
            //每个下标代表那个元素，数组内容则是指是属于那一组
            for (int i = 0; i < n; i++) {
                //初始化的时候是每个人一个组
                fa[i] = i;
            }
        }
//这里是对find函数进行了优化，
        //根据并查集的演化来看，最初就是返回当前属于那一组即，只返回fa[x]的值
        //现在就是递归调用找出最终属于那个组的，然后返回组的代号，
        //又加上优化，使之高度变小，不过是逐步优化的
        public int find(int x){
            if(this.fa[x] != x){
                return this.fa[x] = this.find(this.fa[x]);
            }
            return x;
        }

        /*
        这个也是做了优化，最初的联合是指将x的数值变为y的，循环读取。嫌弃太慢
        原型是find(x) != find(y),fori  if fa[i] == x; fa[[] = y

        后来就是查找组，如果不是就将y加入到x中
         */
        public void union(int x, int y){
            int x_fa = this.find(x);
            int y_fa = this.find(y);

            if(x_fa != y_fa){
                this.fa[y_fa] = x_fa;
            }
        }
    }
}
