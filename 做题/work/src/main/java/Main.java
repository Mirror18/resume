import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;


/**
 * 这是一个操作数据的算法，就是提供数组大小
 * 然后给一个区间添加一个数。
 * 麻烦的在于是提取数据有点麻烦，但其实最后发现这是最简单的
 * 第一行数据 array: 9，提供了一个数字，但是数字前有字符
 * 第二行则是提供了二维数组，例如  update: [[0,2,3],[2,5.-2]]
 * @author mirror
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line1 = scanner.nextLine();
            //这是我第一种处理数据，就硬取出。直接根据位数提取出数字。但不如用下面的
            int n = Integer.parseInt(line1.substring(8));
//            System.out.println(n);
            //最后的结果，但是最后输出是要一个字符串，用sj包裹的，所以这点还能优化
            int[] ans = new int[n];
            String line2 = scanner.nextLine();
            //跟上面同样的道理，硬取，但是没啥用，这俩可以当作没有，
            //这里还保存的原因是最后调试的时候，可以快速跳过无用的数据。
            String a = line2.substring(9);
////            System.out.println(n);
////            System.out.println(line3);
            String b = a.substring(1,a.length()-1);
////            System.out.println(b);
////            System.out.println(b.length());
////            int n2 = (b.length()+2)/11;
////            System.out.println(n2);
//            String[] c = b.split(",");
//            System.out.println(Arrays.toString(c));
            //因为最开始不知道update的数组有多少，所以只能用可以ArrayList来存入
            ArrayList<String> list = new ArrayList<>();
            //缓存，用于组装
            StringBuffer sb = new StringBuffer();
            //对字符串进行处理
            for (int i = 0; i < b.length(); i++) {
                char e = b.charAt(i);
                //这就是我说的处理方法，因为数字的char只能在这个返回内，同时也要包裹一个符号，用于复数
                if(e >= '0' && e <= '9'|| e == '-'){
                    sb.append(b.charAt(i));
                    //这里要注意，jdk1.8不支持，sb.isEmpty()的方法，所以就成了判断长度是否为空
                }else if(sb.length()!=0){
                    //因为到这里的条件是数据已经包裹好了，所以进行处理即可
                    list.add(sb.toString());
                    //这里可以进行优化，因为我忘记怎么删除sb内的数据了，所以直接创建了一个新的对象
                    sb=new StringBuffer();
                }
            }
            //这里是为了取出有多少的数据，方便下一步的转换为二维数组
            int n2 = list.size()/3;
            int[][] arr = new int[n2][3];
            //这里的逻辑很简单，因为仔细想下数据应该存放的位置就ok
            for (int i = 0; i < list.size(); i++) {
                arr[i/3][i%3] = Integer.parseInt(list.get(i));
            }
//            System.out.println(Arrays.deepToString(arr));
            //这里就是数据处理了，这道题的逻辑处理部分就这么一点
            for (int i = 0; i < n2; i++) {
                for (int j = arr[i][0]; j <= arr[i][1]; j++) {
                    ans[j]+=arr[i][2];
                }
            }
            //这里是针对输出的，没办法，我本来是想输出数组就完事了，奈何无法通过
            StringJoiner stringJoiner = new StringJoiner(",","[","]");
            for (int i = 0; i < ans.length; i++) {
                stringJoiner.add(String.valueOf(ans[i]));
            }
            System.out.println(stringJoiner);
        }

    }
}