package com.mirror.智能成绩表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    static class Student {
        String name;
        int[] rank;

        public Student(String name, int[] rank) {
            this.name = name;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //学生人数
        int n = scanner.nextInt();
        //科目数量
        int m = scanner.nextInt();
        //key 是 科目名称， val是科目出现的顺序
        HashMap<String, Integer> subject_rankIdx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            subject_rankIdx.put(scanner.next(), i);
        }
        //存储学生
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            //0 到 m - 1 是学科。 m是总分
            //rank 记录学生排名的要素
            int[] rank = new int[m + 1];
            //总分
            int score_sum = 0;
            for (int j = 0; j < m; j++) {
                rank[i] = scanner.nextInt();
                score_sum += rank[j];
            }
            rank[m] = score_sum;
            students.add(new Student(name, rank));
        }
        //输入用作排名的科目名称。如果没有就取m
        int rankIdx = subject_rankIdx.getOrDefault(scanner.next(), m);
        //排序
        students.sort(
                (a, b) ->
                        a.rank[rankIdx] != b.rank[rankIdx]
                                ? b.rank[rankIdx] - a.rank[rankIdx]
                                : a.name.compareTo(b.name)
        );

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Student s : students) {
            stringJoiner.add(s.name);
        }

        System.out.println(stringJoiner);
    }
}
