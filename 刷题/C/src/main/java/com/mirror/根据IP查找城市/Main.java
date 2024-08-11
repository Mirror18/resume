package com.mirror.根据IP查找城市;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    static class Range {
        String city;
        long startIpDec;
        long endIpDec;
        long ipLen;

        public Range(String city, String startIpStr, String endIpStr) {
            this.city = city;
            this.startIpDec = ip2dec(startIpStr);
            this.endIpDec = ip2dec(endIpStr);
            this.ipLen = this.endIpDec - this.startIpDec + 1;
        }
    }
        static long ip2dec(String ip) {
            long res = 0;
            String[] blocks = ip.split("\\.");
            //通过右移动来进行相加
            for (String block : blocks) {
                res = (Integer.parseInt(block)) | (res << 8);
            }
            return  res;
        }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Range> ranges = new ArrayList<>();

        // 城市IP列表
        String[] cities = sc.nextLine().split(";");
        // 带查询的IP列表
        String[] queryIps = sc.nextLine().split(",");

        // 提取各个城市IP列表信息
        for (String city : cities) {
            String[] tmp = city.split("[=,]");
            ranges.add(new Range(tmp[0], tmp[1], tmp[2]));
        }

        StringJoiner sj = new StringJoiner(",");

        // 遍历待查询的IP地址
        for (String ip : queryIps) {
            long ipDec = ip2dec(ip);

            // 记录该目标IP地址的最佳匹配城市
            String city = "";
            // 记录最佳匹配城市IP段的长度
            long minLen = Long.MAX_VALUE;

            // 将带查询IP与城市IP段列表逐一匹配
            for (Range range : ranges) {
                // 如果带查询的IP地址 在某城市的IP段范围内，且该城市的IP段长度更小，则该城市为待查询IP的最佳匹配城市
                if (ipDec >= range.startIpDec && ipDec <= range.endIpDec && minLen > range.ipLen) {
                    city = range.city;
                    minLen = range.ipLen;
                }
            }

            sj.add(city);
        }

        System.out.println(sj);
    }
}
