import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

// test case
//5
//12341234
//11111222
//12122222
//22222222
//12673939
// result
//4
//2
//5
//2
//14


public class ALGOSPOT_PI {

    static String str;
    static int N;

    static int check(int pos, int len) {
        int i = 1;
        while (i < len && str.charAt(pos + i - 1) == str.charAt(pos + i)) ++i;
        if (i == len) return 1;
        i = 2;
        int diff = Character.getNumericValue(str.charAt(pos + 1)) - Character.getNumericValue(str.charAt(pos));
        while (i < len) {
            int nowDiff = Character.getNumericValue(str.charAt(pos + i)) - Character.getNumericValue(str.charAt(pos + i - 1));
            if (nowDiff != diff) break;
            ++i;
        }
        if (i == len) {
            if (Math.abs(diff) == 1) return 2;
            else return 5;
        }
        i = 2;
        while (i < len && str.charAt(pos + i) == str.charAt(pos + i - 2)) ++i;
        if (i == len) return 4;
        return 10;
    }

    static final int MAXN = 10000;
    static final int INF = 987654321;
    static int memo[];

    static int dp(int pos) {
        if (pos == N) return 0;
        // you can remove this if you put an if in for statement.

        int ret = memo[pos];
        if (ret != -1) return ret;
        ret = INF;
        for (int i = 3; i <= 5; ++i) {
            if (pos + i <= N) ret = min(ret, check(pos, i) + dp(pos + i));
        }
        return memo[pos] = ret;
    }

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; ++i) {
            str = sc.next();
            N = str.length();

            memo = new int[MAXN + 1];
            Arrays.fill(memo, -1);
            System.out.println(dp(0));
        }
    }

    public static void main(String[] args) {
        driver();
    }
}
