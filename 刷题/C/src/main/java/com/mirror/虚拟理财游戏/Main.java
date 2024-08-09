package com.mirror.虚拟理财游戏;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] tmp = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //产品数
        int m = tmp[0];
        //总投资额
        int n = tmp[1];
        //总风险
        int x = tmp[2];
        //产品回报率
        int[] back = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //产品风险值
        int[] risk = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //产品投资额
        int[] invest = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //最大投资回报 = 投资额 * 回报率
        int max_invest_back = 0;
        //这里采用双重循环暴力求解，
        //因为最多只能投资两个，所以用了暴力求解，不然就变成了01背包问题，嗯，不是多会
        HashMap<Integer, Integer> select = new HashMap<>();
        //开始对产品的循环
        for (int i = 0; i < m; i++) {
            //如果单个产品投资风险没超过，那么就投资单个
            if (risk[i] <= x) {
                //第一个产品的投资金额，在最大限制中和总投资金额中选择最小的
                int investI = Math.min(invest[i], n);
                //投资回报
                int invest_back = investI * back[i];
                //这里进行比较，如果比原先的投资回报大，那么就选择投资这个
                if (invest_back > max_invest_back) {
                    max_invest_back = invest_back;
                    select.clear();
                    select.put(i, investI);
                }
            } else {
                //对于单个产品投资风险大于能接受的风险就跳过
                continue;
            }
            //这里还可以选择第二个产品
            for (int j = i + 1; j < m; j++) {
                //如果总风险没超过的话
                if (risk[i] + risk[j] <= x) {
                    //第一产品投资金额
                    int investI;
                    //第二产品投资金额
                    int investJ;
                    //根据自己的总资产还有投资回报率进行比较
                    //因为自己的总资产是有限的
                    if (back[i] > back[j]) {
                        investI = Math.min(invest[i], n);
                        investJ = Math.min(invest[j], n - investI);
                    }else{
                        investJ = Math.min(n,invest[j]);
                        investI = Math.min(n-investJ,invest[i]);
                    }
                    //计算投资回报
                    int invest_back = investI * back[i] + investJ * back[j];
                    //如果昂钱产品组合回报更大，则就购买当前组合产品
                    if(invest_back > max_invest_back){
                        max_invest_back = invest_back;
                        select.clear();
                        //记录产品ID，还有投资额
                        if(investI > 0){
                            select.put(i,investI);
                        }
                        if(investJ > 0){
                            select.put(j,investJ);
                        }
                    }
                }
            }
        }
        //进行输出
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 0; i < m; i++) {
            //查找产品ID，并加入产品的投资额
            if(select.containsKey(i)){
                stringJoiner.add(select.get(i) + "");
            }else {
                stringJoiner.add("0");
            }
        }
        System.out.println(stringJoiner);
    }
}
