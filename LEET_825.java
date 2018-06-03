import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LEET_825 {

    static int [] arr;

    static boolean check(int A, int B) {
        if (A * 0.5 + 7 >= B) return true;
//        if (B > 100 && A < 100) return true;
        return B > A;
    }

    static int exhaustive(int idx) {

        int ret = 0;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
//                if (arr[i] > 100 && arr[j] < 100) break;

                // i : A, j : B
                if (check(arr[i], arr[j])) continue;
                ++ret;

                // i : B, j : A
                if (check(arr[j], arr[i])) continue;
                ++ret;
            }
        }

        return ret;
    }

    public static int numFriendRequests(int[] ages) {

        Arrays.sort(ages);
        int halfLen = ages.length / 2;
        for (int i = 0; i < halfLen; ++i) {
            int t = ages[i];
            ages[i] = ages[ages.length - i - 1];
            ages[ages.length - i - 1] = t;
        }

        arr = ages;
        return exhaustive(0);



//        for (int i = 0; i < ages.length; ++i) System.out.println(ages[i]);

//        return 0;
    }

    public static void main(String[] args) {
//        int arr[] = {16, 16};
//        int arr[] = {16, 17, 18};
//        int arr[] = {20,30,100,110,120};
        int arr[] = {101,56,69,48,30};
        System.out.println(numFriendRequests(arr));
    }
}
