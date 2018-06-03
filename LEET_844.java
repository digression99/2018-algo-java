public class LEET_844 {

    static String trimString(String S) {

        StringBuilder sb = new StringBuilder(S);

        int it = 0;
        while (it != sb.length()) {
            if (sb.charAt(it) == '#') {
                if (it >= 1) {
                    sb.delete(it - 1, it);
                    --it;
                }
                sb.deleteCharAt(it);
            } else {
                ++it;
            }
        }
        return sb.toString();
    }

    public boolean backspaceCompare(String S, String T) {

        String trimmedS = trimString(S);
        String trimmedT = trimString(T);
        return trimmedS.equals(trimmedT);
    }

    public static void test() {
        String str1 = "ab##";
        String str2 = "c#d#";
//        StringBuilder sb = new StringBuilder(str);

        String trimmed1 = trimString(str1);
        String trimmed2 = trimString(str2);

        System.out.println(trimmed1);
        System.out.println(trimmed2);
    }

    public static void main(String [] args) {
//        System.out.println("hi");
        test();
    }
}
