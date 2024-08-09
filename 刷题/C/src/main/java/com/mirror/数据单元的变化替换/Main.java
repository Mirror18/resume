package com.mirror.数据单元的变化替换;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//一个简单的代码，但是不能解决循环引用的事
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String input = scanner.nextLine();
//        String[] cells = input.split(",");
//
//        for (String cell : cells) {
//            if(cell.contains("<") && cell.contains(">")){
//                String ref = cell.substring(cell.indexOf('<') + 1 , cell.indexOf('>'));
//                if(ref.length() != 1 || ref.charAt(0) <'A' || ref.charAt(0) >'Z'|| ref.charAt(0) - 'A' >= cell.length()){
//                    System.out.println("-1");
//                    return;
//                }
//            }
//        }
//
////        for (int i = 0; i < cells.length; i++) {
////            String cell =cells[i];
////
////            if(cell.contains("<") && cell.contains(">")){
////                String ref = cell.substring(cell.indexOf('<') + 1 , cell.indexOf('>'));
////                int refIndex = ref.charAt(0) - 'A';
////                cells[i] = cell.replaceAll("<" + ref + ">" ,cells[refIndex]);
////            }
////        }
//
//        boolean updated;
//        do {
//            updated = false;
//            for (int i = 0; i < cells.length; i++) {
//                String cell = cells[i];
//                if (cell.contains("<") && cell.contains(">")) {
//                    String ref = cell.substring(cell.indexOf('<') + 1, cell.indexOf('>'));
//                    int refIndex = ref.charAt(0) - 'A';
//                    if (cells[refIndex].contains("<") && cells[refIndex].contains(">")) {
//                        updated = true;
//                    }
//                    cells[i] = cell.replace("<" + ref + ">", cells[refIndex]);
//                }
//            }
//        } while (updated);
//
//        System.out.println(String.join(",",cells));
//    }
//}

//想要解决循环引用则就是要考虑递归

public class Main {
    static String[] cells;
    static Pattern p = Pattern.compile("(<.*?>)");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        cells = scanner.nextLine().split(",");

        System.out.println(getResult());
    }

    private static String getResult() {
        StringJoiner stringJoiner = new StringJoiner(",");

        for (int i = 0; i < cells.length; i++) {
            if (changeCell(i)) {
                return "-1";
            }
            stringJoiner.add(cells[i]);
        }
        return stringJoiner.toString();
    }

    private static boolean changeCell(int index) {
        Matcher m = p.matcher(cells[index]);
        //如果有匹配到的子串
        while (m.find()) {
            //这里指向被匹配的内容
            String reference = m.group(1);

            if (reference.length() != 3) {
                return true;
            }
            //引用的单元格
            char reference_cellNum = reference.charAt(1);
            //自身单元格
            char self_cellNum = (char) ('A' + index);
            //首先解决的是单元格被替换的字符是否正确，并且还不能自己匹配自己
            if (reference_cellNum < 'A' || reference_cellNum > 'Z' || reference_cellNum == self_cellNum) {
                return true;
            }
            //这是匹配到的单元格在数组中的位置
            int reference_index = reference_cellNum - 'A';
            //如果位置超出长度
            if(reference_index >= cells.length){
                return true;
            }
            //这是针对循环引用设置的
            if(changeCell(reference_index)){
                return true;
            }
            //开始替换
            cells[index] = cells[index].replaceAll(reference,cells[reference_index]);
            //重新正则匹配，虽然没啥用。因为虽然是while循环，但只有单个引用
            //所以有没有都行
            m = p.matcher(cells[index]);
        }
        return false;
    }
}