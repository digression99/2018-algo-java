import java.util.*;

import static java.lang.StrictMath.max;

// test case
//3
//he?p
//3
//help
//heap
//helpp
//*p*
//3
//help
//papa
//hello
//*bb*
//1
//babbbc

// results
//heap
//help
//help
//papa
//babbbc


public class ALGOSPOT_WILDCARD {

    static String pat, str;
    static int W, N;
    static int memo[][];

    static int exhaustive(int patLen, int strLen) {
        // base case
        if (patLen >= W && strLen >= N) return 1;
        if (patLen >= W) return 0;
//        if (!(patLen < W && strLen < N)) return 0;
        int res = 0;
        char c = pat.charAt(patLen);

        if (c == '?') {
            res = exhaustive(patLen + 1, strLen + 1);
        } else if (c == '*') {
//            res = max(res, exhaustive(patLen))
            for (int i = 0; i < N - strLen + 1; ++i)
                res = max(res, exhaustive(patLen + 1, strLen + i));
        } else if (strLen < N && c == str.charAt(strLen))
            res = exhaustive(patLen + 1, strLen + 1);
        return res;
    }

    static int dp(int patLen, int strLen) {
        if (memo[patLen][strLen] != -1) return memo[patLen][strLen];

        while (patLen < W && strLen < N && (pat.charAt(patLen) == '?' || pat.charAt(patLen) == str.charAt(strLen))) {
            ++patLen;
            ++strLen;
        }
        if (patLen == W) {
            if (strLen == N) return memo[patLen][strLen] = 1;
            return memo[patLen][strLen] = 0;
        }
        if (pat.charAt(patLen) == '*') {
            for (int skip = 0; skip + strLen <= N; ++skip) {
                int res = dp(patLen + 1, strLen + skip);
                if (res == 1) return memo[patLen][strLen] = 1;
            }
        }
        return memo[patLen][strLen] = 0;
    }

//    static int dp(int patLen, int strLen) {
//        // base case.
//        if (patLen >= W && strLen >= N) return 1;
//        if (patLen >= W) return 0;
////        String kim = "kimilsik";
////        kim.split();
//
//        // memo.
//        if (strLen < N && memo[patLen][strLen] != -1) return memo[patLen][strLen];
//        int res = 0;
//        char c = pat.charAt(patLen);
//
//        if (c == '?') {
//            res = max(res, dp(patLen + 1, strLen + 1));
//        } else if (c == '*') {
//            for (int i = 0; i < N - strLen + 1; ++i)
//                res = max(res, dp(patLen + 1, strLen + i));
//        } else if (strLen < N && c == str.charAt(strLen))
//            res = max(res, dp(patLen + 1, strLen + 1));
//        return memo[patLen][strLen] = res;
//    }

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; ++i) {
            pat = sc.next();
            W = pat.length();
            int n = sc.nextInt();
            ArrayList<String> ans = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                str = sc.next();
                N = str.length();
                memo = new int[W + 1][N + 1];
                for (int k = 0; k <= W; ++k) Arrays.fill(memo[k], -1);

//                if (exhaustive(0, 0) == 1)
//                    System.out.println(str);
                if (dp(0, 0) == 1) ans.add(str);
//                if (memo[W][N] == 1) System.out.println(str);

//                if (dp(0,0) == 1) System.out.println(str);
            }
            ans.sort(new Comparator<String> () {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
//            Collections.sort(ans, new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    return o1.compareTo(o2);
//                }
//            });

            for (String dat : ans) System.out.println(dat);
        }
    }

    public static void main(String[] args) {
        driver();
    }
}
