package com.mirror.开源项目热度榜单;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] weights = new int[5];
        for (int i = 0; i < 5; i++) {
            weights[i] = scanner.nextInt();
        }
        Project[] projects = new Project[n];
        for (int i = 0; i < n; i++) {
            String name = scanner.next();

            int hot = 0;
            for (int j = 0; j < 5; j++) {
                hot += scanner.nextInt() * weights[j];
            }
            projects[i] = new Project(name, hot);
        }
        Arrays.sort(projects, (a, b) ->
                a.hot != b.hot ? b.hot - a.hot :
                        a.name.toLowerCase().compareTo(b.name.toLowerCase())
        );

        for (Project project : projects) {
            System.out.println(project.name);
        }
    }

    static class Project {
        String name;
        int hot;

        public Project(String name, int hot) {
            this.hot = hot;
            this.name = name;
        }
    }
}
