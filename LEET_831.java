public class LEET_831 {

    public static String maskPII(String str) {

        // if str is email? it contains @.
        // if str is phone number? it contains -.

//        str.match

        if (isPhoneNumber(str)) {
            // this is phone number.
            boolean isCountryCode = countDigits(str) > 10;

            String[] splitted = str.split("[() -]");
            StringBuilder combined = new StringBuilder();
            for (int i = 1; i < splitted.length; ++i) combined.append(splitted[i]);

            String ret = "";

            // you forgot the country code.
            // use the first index.
            String res = combined.toString();
            int l = res.length();

            if (isCountryCode) {
                ret += "+";
                for (int i = 0; i < l - 10; ++i) ret += "*";
                ret += "-";
            }
            ret += "***-***-";
            ret += res.substring(l - 4, l);
            return ret;
        } else {
            // this is email.
            str = str.toLowerCase();
            String[] splitted = str.split("[@.]");
            char s = splitted[0].charAt(0);
            char e = splitted[0].charAt(splitted[0].length() - 1);

            return Character.toString(s) + "*****" + Character.toString(e) +
                    "@" + splitted[1] + "." + splitted[2];
        }
    }

    static boolean isPhoneNumber(String str) {

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '(' || c == ')' || c == ' ' || c == '-') return true;
        }

        return false;
    }

    static int countDigits(String str) {
        int ret = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) <= '9' && str.charAt(i) >= '0') ++ret;
        }

        return ret;
    }

    static void test() {

        String str = "LeetCode@LeetCode.com";
        str = str.toLowerCase();

//        CharMatcher

//        String[] arr = str.split("[@.]");

//        for (int i = 0; i < arr.length; ++i) {
//            System.out.println(arr[i]);
//        }

//        System.out.println("(3906)2 07143 711".contains("[]"));
//        System.out.println("(3906)2 07143 711".matches("[(]"));

//        if (str.contains("@")) {
//            System.out.println("T");
//        }

//        System.out.println(str);
    }



    public static void main(String[] args) {
//        test();
//        System.out.println(maskPII("LeetCode@LeetCode.com"));
//        System.out.println(maskPII("(3906)2 07143 711"));
        System.out.println(maskPII("86-(10)12345678"));
//        System.out.println(maskPII("1(234)567-890"));
    }
}
