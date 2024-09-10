import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            getResult(s);
        }
    }

    private static void getResult(String s) {
        StringBuffer stringBuffer = new StringBuffer();

        int a = 0;
        int n = s.length();
        int ans = n / 8;
        if ("".equals(s)) {
            System.out.println(s);
            return;
        }

        if (n % 8 != 0) {
            a = 8 - n % 8;
            stringBuffer.append(s);
            while (a != 0) {
                stringBuffer.append("0");
                a--;
            }
            ans++;
            s = stringBuffer.toString();
        }
        for (int i = 0; i < ans; i++) {
            System.out.println(s.substring(0, 8));
            s = s.substring(8);
        }
    }
}