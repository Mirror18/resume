package com.mirror.计算三叉搜索树高度;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取输入
        int n = Integer.parseInt(scanner.nextLine());
        //创建一个三叉搜索树
        Tree tree = new Tree();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            tree.add(num);
        }

        System.out.println(tree.height);
    }



//节点的数据结构
     static class  TreeNode{
        //节点值
        int val;
        //节点所在的高度
        int height;
        TreeNode left;
        TreeNode mid;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }
//整体树的世界结构
    static class Tree{
        //保留一个根节点和树高
        TreeNode root;
        int height;
        //添加时候的逻辑
        public void add(int val){
            //首先是创建一个节点
            TreeNode node = new TreeNode(val);
            //如果是头次创建的话
            //第一次添加节点
            if(this.root == null){
                node.height = 1;
                this.root = node;
                this.height = 1;
            }else{
                //首先是确定根节点
                TreeNode cur = this.root;
                //然后从根节点进行判断应该添加在何处
                while (true){
                    //因为根节点只可能有一个，锁以新添加的节点所在高度 +1
                    node.height = cur.height + 1;
                    //树高则就是当前树的高度或者说是节点所在高度
                    this.height = Math.max(node.height, this.height);
                    //开始迭代查询新添加的节点应该添加在哪里
                    //首先是满足左子树的请求，放到左子树中，并判断是否有节点
                    //没有的话就添加成功，下方三个节点添加原理类似
                    if(val < cur.val - 500){
                        if(cur.left == null){
                            cur.left = node;
                            break;
                        }else {
                            //没有的话就继续迭代查询
                            cur = cur.left;
                        }
                    }else if(val > cur.val + 500){
                        if(cur.right == null){
                            cur.right = node;
                            break;
                        }else{
                            cur = cur.right;
                        }
                    }else{
                        if(cur.mid == null){
                            cur.mid = node;
                            break;
                        }else{
                            cur = cur.mid;
                        }
                    }
                }
            }
        }
    }
}
