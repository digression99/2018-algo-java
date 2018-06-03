import static java.lang.Integer.max;

public class LEET_845 {

    static int countLength(int[] A, int idx) {
        int ret = 1;
        boolean down = false;

        while (idx + 1 < A.length && A[idx] < A[idx + 1]) {
            ++ret;
            ++idx;
        }
        while (idx + 1 < A.length && A[idx] > A[idx + 1]) {
            down = true;
            ++ret;
            ++idx;
        }
        return down && ret >= 3 ? ret : 0;
    }

    public static int longestMountain(int[] A) {
        int pos = 0;
        int maxLen = -1;
        int nowLen = 0;

        while (pos + 1 < A.length) {
            if (A[pos] < A[pos + 1]) {
                nowLen = countLength(A, pos);
                if (nowLen == 0) {
                    ++pos; continue;
                }
                maxLen = max(maxLen, nowLen);
                pos += nowLen - 1;
            } else ++pos;
        }
        return maxLen == -1 ? 0 : maxLen;
    }

    public static void test() {
//        int[] A = {2, 1, 4, 7, 3, 2, 5};
        int[] A2 = {0,1,2,3,4,5,4,3,2,1,0};
        int[] A3 = {2, 3};
        int[] A4 = {0,1,2,3,4,5,6,7,8,9};
        int[] A5 = {875,884,239,731,723,685};


//        int res = countLength(A, 1);
        int res = longestMountain(A5);
        System.out.println(res);
    }

    public static void main(String[] args) {

        test();
    }
}
