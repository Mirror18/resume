package com.mirror.生成哈夫曼树;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    static class Node {
        Node lchild;
        Node rchild;
        int weight;
        int height;

        public Node(Node lc, Node rc, int weight, int height) {
            this.height = height;
            this.lchild = lc;
            this.rchild = rc;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        //制定排序规则，优先队列
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) ->
                        a.weight != b.weight
                                ? a.weight - b.weight
                                : a.height - b.height//这里的排序规则是左右权值相同，左子树高度小雨柚子树
        );
        //进行添加节点
        for (int i = 0; i < n; i++) {
            //创建n个哈夫曼树节点
            int w = scanner.nextInt();
            Node node = new Node(null,null, w,0);
            //加入优先队列
            pq.offer(node);
        }
        //当经过多次合并之后，剩下最后一个节点就是根节点
        while(pq.size() >1){
            //取出最小的权值节点
            Node lc = pq.poll();
            Node rc = pq.poll();
            //将左右节点进行合并
            int fa_weight = lc.weight + rc.weight;
            //右子树的高度是＞左子树的
            int fa_height = rc.height + 1;
            //新街店加入优先队列
            Node fa = new Node(lc, rc,fa_weight,fa_height);
            pq.offer(fa);
        }
        //最后一个节点必然是根节点，此时进行中序遍历
        Node root = pq.poll();
        StringJoiner stringJoiner = new StringJoiner(" ");
        midOrder(root,stringJoiner);

        System.out.println(stringJoiner);
    }

    private static void midOrder(Node root, StringJoiner stringJoiner) {
        if(root.lchild != null){
            midOrder(root.lchild, stringJoiner);
        }
        stringJoiner.add(root.weight +"");

        if(root.rchild != null){
            midOrder(root.rchild, stringJoiner);
        }
    }
}
