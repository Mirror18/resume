package com.mirror.二叉树计算;

import java.util.*;

public class Main {
    static class TreeNode{
        int num;
        int childSum;
        TreeNode leftChild;
        TreeNode rightChild;
        
        public TreeNode(int num){
            this.num = num;
            this.childSum = 0;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    
    static int[] midOrder;
    static int[] preOrder;
    
    static HashMap<Integer, ArrayList<Integer>> midIndexMap = new HashMap<>();
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        midOrder = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        preOrder = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        int n = midOrder.length;
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];;
            midIndexMap.putIfAbsent(num,new ArrayList<>());
            midIndexMap.get(num).add(i);
        }
        
        TreeNode root = buildTree(0,n-1,0,n-1);

        StringJoiner stringJoiner = new StringJoiner(" ");
        getMidOrder(root,stringJoiner);
        System.out.println(stringJoiner);
    }

    private static void getMidOrder(TreeNode root, StringJoiner stringJoiner) {
        if(root == null){
            return;
        }

        TreeNode leftChild = root.leftChild;
        if(leftChild != null){
            getMidOrder(leftChild,stringJoiner);
        }
        stringJoiner.add(root.childSum +"");

        TreeNode rightChild = root.rightChild;
        if(rightChild != null){
            getMidOrder(rightChild,stringJoiner);
        }
    }

    private static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        if(preL > preR){
            return null;
        }

        int rootNum = preOrder[preL];
        TreeNode root = new TreeNode(rootNum);

        for (int idx : midIndexMap.get(rootNum)) {
            if(idx < midL || idx > midR){
                continue;
            }

            int leftLen = idx - midL;
            if(notEquals(midL,preL+1,leftLen)){
                continue;
            }
            int rightLen = midR - idx;
            if(notEquals(idx +1,preR - rightLen + 1,rightLen)){
                continue;
            }

            root.leftChild = buildTree(midL,idx -1, preL +1,preL+leftLen);

            root.rightChild = buildTree(idx+1,midR,preR- rightLen + 1,preR);

            root.childSum =
                    (root.leftChild == null ? 0 : (root.leftChild.num + root.leftChild.childSum))
                    +(root.rightChild == null ? 0 :(root.rightChild.num + root.rightChild.childSum));
            break;
        }
        return root;
    }

    private static boolean notEquals(int midL, int preL, int size) {
        int[] arr1 = Arrays.stream(Arrays.copyOfRange(midOrder,midL,midL+size)).sorted().toArray();
        int[] arr2 = Arrays.stream(Arrays.copyOfRange(preOrder,preL,preL+size)).sorted().toArray();

        for (int i = 0; i < size; i++) {
            if(arr1[i] != arr2[i]){
                return true;
            }
        }
        return false;
    }
}
