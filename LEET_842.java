import java.util.ArrayList;
import java.util.List;

class LEET_842 {

    static String str = "";
    static final int MAX_INT = 2147483647;

    static void test() {

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(10);
        ans.add(20);
        ans.add(30);

        ans.remove(ans.size() -1);

        for (int i = 0; i < ans.size(); ++i) System.out.print(ans.get(i) + " ");

//        String S = "123456";
//
//        String sub = S.substring(0, 3);
//
//        System.out.println("sub is : " + sub);
//        int num = Integer.parseInt(sub);
//
//        System.out.println(num);
    }

    static boolean fiboCheck(ArrayList<Integer> ans) {
        for (int i = 0; i < ans.size() - 2; ++i) {
            if (ans.get(i) + ans.get(i + 1) != ans.get(i + 2)) return false;
        }
        return true;
    }

    static boolean search(ArrayList<Integer> ans, int start) {

        if (start == str.length()) {
            // check if it's fibo.
            return fiboCheck(ans) & ans.size() >= 3;
        }

        if (ans.size() >= 3) {
            int f1 = ans.get(ans.size() - 3);
            int f2 = ans.get(ans.size() - 2);
            int f3 = ans.get(ans.size() - 1);
            if (f1 + f2 != f3) return false;
        }

        // remove leading zero.
//        if (str.charAt(start) == '0') return false;

        for (int i = start + 1; i <= str.length(); ++i) {
            int len = i - start;
            if (len <= 10 & (len == 1 || (len >= 2 & str.charAt(start) != '0'))) {
                long num = Long.parseLong(str.substring(start, start + len));
                if (num >= 0 & num <= MAX_INT) {
                    ans.add((int) num);
                    if (search(ans, i)) return true;
                    ans.remove(ans.size() - 1);
                }
            }
        }
        return false;
    }


    public static List<Integer> splitIntoFibonacci(String S) {

        ArrayList<Integer> ans = new ArrayList<>();
        str = S;

        search(ans, 0);
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println("hi!");
//        test();
        String testcase1 = "11235813";
        String testcase2 = "123456579";

        List<Integer> ans = splitIntoFibonacci(testcase1);
        for (int i = 0; i < ans.size(); ++i) {
            System.out.println(ans.get(i));
        }
    }
}
