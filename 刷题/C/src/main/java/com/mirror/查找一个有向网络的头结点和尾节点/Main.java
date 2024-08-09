package com.mirror.查找一个有向网络的头结点和尾节点;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //关系数量
        int n = scanner.nextInt();
        //存储入度
        HashMap<Integer,Integer> inDegree = new HashMap<>();
        //存储父子关系
        HashMap<Integer, ArrayList<Integer>> next = new HashMap<>();
        //独立节点
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            //先添加独立单位
            set.add(a);
            set.add(b);
            //然后添加入度
            inDegree.put(b,inDegree.getOrDefault(b,0) + 1);
            //a的后继节点放入
            next.putIfAbsent(a,new ArrayList<>());
            //添加队列
            next.get(a).add(b);
        }
        //元素总数
        int total = set.size();
        //头结点
        int head = 0;
        //用来存储入度为0 的点
        LinkedList<Integer> queue = new LinkedList<>();
        for (int p : set) {
            //首先初始化的时候，一定有一个没有添加到入度中，那个就是头结点
            if(!inDegree.containsKey(p)){
                head = p;
                queue.add(p);
                break;
            }
        }
        //用来存储出度为0 的
        ArrayList<Integer> tails = new ArrayList<>();
        //记录已经剥去的节点个数，因为要处理环的事
        int count = 0;
        //处理取出头结点之后的关系的
        while(queue.size() > 0){
            //剥离入度为0的点
            int fa = queue.removeFirst();
            count++;
            //如果本身就没存储后继节点的话，那么证明出度为0
            if(!next.containsKey(fa)){
                tails.add(fa);
                continue;
            }
            //这是关于hashmap的特性，再次放入的时候是会更新，所以更新入度 -1
            for (int ch : next.get(fa)) {
                inDegree.put(ch,inDegree.get(ch) -1);
                //当没有环的情况下，去掉一个节点后，会正常出现一个入度为 0的，继续添加
                if(inDegree.get(ch) == 0){
                    queue.add(ch);
                }
            }
        }
        //证明结构中存在环
        if(count != total){
            System.out.println(-1);
        }else{
            StringJoiner stringJoiner = new StringJoiner(" ");

            stringJoiner.add(head + "");

            tails.stream().sorted((a,b) -> a -b).forEach(p -> stringJoiner.add(p+""));

            System.out.println(stringJoiner);
        }
    }
}
