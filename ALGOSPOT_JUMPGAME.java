import java.util.Arrays;
import java.util.Scanner;

// test case
//2
//7
//2 5 1 6 1 4 1
//6 1 1 2 2 9 3
//7 2 3 2 1 3 1
//1 1 3 1 7 1 2
//4 1 2 3 4 1 2
//3 3 1 2 3 4 1
//1 5 2 9 4 7 0
//7
//2 5 1 6 1 4 1
//6 1 1 2 2 9 3
//7 2 3 2 1 3 1
//1 1 3 1 7 1 2
//4 1 2 3 4 1 3
//3 3 1 2 3 4 1
//1 5 2 9 4 7 0

//result
//YES
//NO

public class ALGOSPOT_JUMPGAME {

    static int board[][];
    static int N;
    static int memo[][];

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int i = 0; i < tc; ++i ) {
            N = sc.nextInt();
            board = new int[N + 1][N + 1];
            memo = new int[N + 1][N + 1];

            for (int j = 0; j < N; ++j) Arrays.fill(memo[j], -1);

            for (int j = 0; j < N; ++j)
                for (int k = 0; k < N; ++k)
                    board[j][k] = sc.nextInt();
            
//            System.out.println(exhaustive(0, 0));

            int result = dp(0, 0);
            if (result == 0) System.out.println("NO");
            else System.out.println("YES");
        }

    }

    private static int exhaustive(int y, int x) {
        if (y < 0 || y >= N || x < 0 || x >= N) return 0;
        if (board[y][x] == 0) return 1;
        int right = exhaustive(y, x + board[y][x]);
        int down = exhaustive(y + board[y][x], x);
        if (right == 1 || down == 1) return 1;
        return 0;
    }

    static int dp(int y, int x) {
        // base case
//        if (y < 0 || y >= N || x < 0 || x >= N) return 0;
        if (y >= N || x >= N) return 0;
        if (board[y][x] == 0) return 1;

        // memo
        if (memo[y][x] != -1) return memo[y][x];

        int right = dp(y, x + board[y][x]);
        int down = dp(y + board[y][x], x);
        if (right == 1 || down == 1) return memo[y][x] = 1;
        return memo[y][x] = 0;
    }

    static void test() {
        boolean test1 = false;
        boolean test2 = true;
//        Boolean.
//        int res = (int)(test1 || test2);
//        int res =

    }

    public static void main(String[] args) {
        driver();
    }
}
