import java.util.*;

/**
 * 题目和测试用例记不清了
 * 简单来说就是最简单的背包问题，
 * 第一行输入的是货物，例如 6,3,6,5
 * 第二行输入的算是货车的承重和数量。例如 5：2， 6：3，1：5
 * 所以处理也很简单
 * @author mirror
 */
public class HuoWu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            //这里获取到两行输入
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            //将货物重量存放到数组中
            int[] a = Arrays.stream(line1.split(",")).mapToInt(Integer::parseInt).toArray();
            //这里是先进行分割
            String[] b = line2.split(",");
            //然后创建一个二维数组用于存放货车承重和数量信息
            int[][] c = new int[b.length][2];
            //这里将数据存放到二维数组中，或许说用map也行，但实际测试比较麻烦，因为还要把key淡出取出来，
            //把keySet()从set转换为数组，因为要满足从小到大比较，所以不如直接用二维数组，后续排序即可
            for (int i = 0; i < b.length; i++) {
                String s = b[i];
                int[] d = Arrays.stream(s.split(":")).mapToInt(Integer::parseInt).toArray();
                c[i] = d;
            }
            System.out.println(getResult(a,c));

        }
    }
    private static int getResult(int[] a,int[][] c){
        //首先对重量进行排序，
        Arrays.sort(a);
        int n1 = a.length;
        int sum = 0;
        //这里是对二维数组排序，必须要写规则哈，e和f代表了一个int[]。要是不排序会报错
        Arrays.sort(c,(e,f)->e[0]-f[0]);
        //这里是测试是否正确排序的
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.deepToString(c));
        //这里的逻辑是用货物重量从大到小，依次测试最近的货车承重
        for (int i = n1-1; i >=0; i--) {
            for (int j = 0; j < c.length; j++) {
                //这里还要确保还有货车数量
                if(a[i]<=c[j][0] &&c[j][1] >0){
                    sum += a[i];
                    c[j][1]--;
                    break;

                }
            }
        }
        //返回的是最终能带走的数量
        return sum;
    }
}
