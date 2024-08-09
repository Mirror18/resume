package com.mirror.二叉树的广度优先遍历;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String post = scanner.next();
        String mid = scanner.next();

        System.out.println(getResult(post,mid));
    }

    private static String getResult(String post, String mid) {

        LinkedList<String[]> queue = new LinkedList<>();

        ArrayList<Character> ans = new ArrayList<>();

        devideLR(post,mid,queue,ans);

        while(queue.size() > 0){
            String[] tmp =queue.removeFirst();
            devideLR(tmp[0],tmp[1],queue,ans);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : ans) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static void devideLR(String post, String mid, LinkedList<String[]> queue, ArrayList<Character> ans) {
        char rootEle = post.charAt(post.length() -1);

        ans.add(rootEle);

        int rootIdx = mid.indexOf(rootEle);

        int leftLen = rootIdx;
        if(leftLen > 0){
            String leftPost = post.substring(0,leftLen);

            String leftMid = mid.substring(0,rootIdx);
            queue.addLast(new String[]{leftPost,leftMid});
        }

        if(post.length() -1 - leftLen > 0){
            String rightPost = post.substring(leftLen,post.length() -1);
            String rightMid = mid.substring(rootIdx+1);

            queue.addLast(new String[]{rightPost,rightMid});
        }
    }
}
