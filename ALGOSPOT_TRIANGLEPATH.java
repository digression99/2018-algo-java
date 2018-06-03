import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;


// test case
//2
//5
//6
//1  2
//3  7  4
//9  4  1  7
//2  7  5  9  4
//5
//1
//2 4
//8 16 8
//32 64 32 64
//128 256 128 256 128

// results
//28
//341

public class ALGOSPOT_TRIANGLEPATH {

    static int memo[][];
    static int board[][];
    static int N;

    static int dp(int row, int col) {
        if (row == N - 1) return board[row][col];
        int ret = memo[row][col];
        if (ret != -1) return ret;

        ret = max(ret, dp(row + 1, col));
        ret = max(ret, dp(row + 1, col + 1));
        return memo[row][col] = ret + board[row][col];
    }

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; ++i) {
            N = sc.nextInt();

            memo = new int[N + 1][N + 1];
            board = new int[N + 1][N + 1];

            for (int j = 0; j < N; ++j) Arrays.fill(memo[j], -1);

            for (int j = 0; j < N; ++j) {
                for (int k = 0; k <= j; ++k) {
                    board[j][k] = sc.nextInt();
                }
            }

            System.out.println(dp(0, 0));
        }

    }

    public static void main(String[] args) {
        driver();
    }
}
