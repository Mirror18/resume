package com.mirror.冠亚军排名.奖牌榜排名;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Country[] countries = new Country[n];
        for (int i = 0; i < n; i++) {
            Country c = new Country(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            countries[i] = c;
        }
        getResult(countries);
    }

    private static void getResult(Country[] countries) {
        Arrays.sort(
                countries,
                (a, b) -> b.gold != a.gold ? b.gold - a.gold :
                        a.silver != b.silver ? b.silver - a.silver :
                                a.bronze != b.bronze ? b.bronze - a.bronze :
                                        a.name.compareTo(b.name)
        );
        for (Country c : countries) {
            System.out.println(c.name);
        }
    }

    private static class Country {
        String name;
        int gold;
        int silver;
        int bronze;

        public Country(String name, int gold, int silver, int bronze) {
            this.name = name;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
}
