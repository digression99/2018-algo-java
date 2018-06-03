import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

// test case
//3
//3 3
//1 2 4
//3 4 7
//3 3
//1 2 3
//4 5 6
//5 3
//10 20 30 1 2
//10 20 30

// results
//5
//6
//5


public class ALGOSPOT_JLIS {

    static int A[], B[];
    static int N, M;
    static int memo[][];
    static final long INF = Long.MIN_VALUE;

    // not mine
    static int jlis(int idxA, int idxB) {
        int ret = memo[idxA + 1][idxB + 1];
        if (ret != -1) return ret;

        ret = 2;

        long a = (idxA == -1 ? INF : A[idxA]);
        long b = (idxB == -1 ? INF : B[idxB]);
        long maxElement = max(a, b);

        for (int nextA = idxA + 1; nextA < N; ++nextA) {
            if (maxElement < A[nextA]) ret = max(ret, jlis(nextA, idxB) + 1);
        }
        for (int nextB = idxB + 1; nextB < N; ++nextB) {
            if (maxElement < B[nextB]) ret = max(ret, jlis(idxA, nextB) + 1);
        }
        return memo[idxA + 1][idxB + 1] = ret;
    }

//    static int lis(int arr[], int pos, int maxIdx) {
//        int ret = 0;
//        for (int i = pos + 1; i <= maxIdx; ++i) {
//            if (arr[pos] < arr[i]) ret = max(ret, lis(arr, i, maxIdx) + 1);
//        }
//        return ret;
//    }

//    static int dp(int pos) {
//        int ret = memo[pos];
//        if (ret != -1) return ret;
//
//        ret = 0;
//        for (int i = pos + 1; i <= N; ++i) {
//            if (seq[pos] < seq[i]) ret = max(ret, dp(i) + 1);
//        }
//        return memo[pos] = ret;
//    }

//    static int exhaustive(int aPos, int bPos) {
//        if (aPos == N + 1 && bPos == M + 1) return 0;
////            return A[aPos] != B[bPos] ? 2 : 1;
//
////        int ret = 1;
//
//        if (aPos == N + 1) {
//            if (A[aPos - 1] < B[bPos]) return lis(B, bPos, M) + 1;
//            else return exhaustive(aPos, bPos + 1);
//        }
//        if (bPos == M + 1) {
//            if (B[bPos - 1] < A[aPos]) return lis(A, aPos, N) + 1;
//            else return exhaustive(aPos + 1, bPos);
//        }
//
//        if (A[aPos] > B[bPos])
//            return exhaustive(aPos, bPos + 1) + 1;
//        else if (A[aPos] < B[bPos])
//            return exhaustive(aPos + 1, bPos) + 1;
//        else if (A[aPos] == B[bPos]) {
////            return exhaustive(aPos + 1, bPos + 1) + 1;
//            return max(exhaustive(aPos + 1, bPos), exhaustive(aPos, bPos + 1)) + 1;
//        }
//
//        return 0;
//    }

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int i = 0; i < tc; ++i) {
//            int n, m;
            N = sc.nextInt();
            M = sc.nextInt();

            A = new int[101];
            B = new int[101];
            memo = new int[101][101];
            for (int j = 0; j < 101; ++j) Arrays.fill(memo[j], -1);

            for (int j = 0; j < N; ++j) A[j] = sc.nextInt();
            for (int j = 0; j < M; ++j) B[j] = sc.nextInt();

            System.out.println(jlis(-1, -1) - 2);
//            System.out.println(exhaustive(0, 0) - 2);
        }
    }

    public static void main(String[] args) {
        driver();
    }
}
