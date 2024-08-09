package com.mirror.执行任务赚积分;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int t = scanner.nextInt();

        int[][] wos = new int[n][2];
        for (int i = 0; i < n; i++) {
            wos[i][0] = scanner.nextInt();
            wos[i][1] = scanner.nextInt();
        }
        System.out.println(getRessult(wos,t));
    }

    private static int getRessult(int[][] wos, int t) {
        //对所需时间大小进行排序
        Arrays.sort(wos,(a,b) -> a[0] - b[0]);;
        //按照积分对任务优先排序。积分越小，优先级越高
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a -b);
        //记录当前时间
        int curTime = 0;
        //结果，最多积分
        int ans = 0;
        for (int[] wo :wos) {
            int endTime = wo[0];
            int score = wo[1];
            //只要当前时间没有超过结束时间，就可以先加入
            if(curTime < endTime){
                pq.offer(score);
                ans+= score;
                curTime++;
            }else{
                //pq里面记录的就是 curTime 之前时刻执行的任务
                if(pq.size() == 0){
                    continue;
                }
                //最小积分，已经执行过的
                int min_score = pq.peek();
                //如果当前任务积分 大于前面时间内可以执行的任务中最小积分
                if(score > min_score){
                    //那么就进行更替
                    pq.poll();
                    pq.offer(score);
                    ans+= score - min_score;
                }
            }
        }
        //最多只能完成 t个任务，多的任务应该去掉，
        while(pq.size() > t && t > 0){
            ans -= pq.poll();
        }
        return ans;
    }
}
