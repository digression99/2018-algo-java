import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class LEET_824 {
    public static String toGoatLatin(String S) {

        String[] stringVec = S.split(" ");
        Character values[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        Set<Character> vowels = new HashSet<>(Arrays.asList(values));
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < stringVec.length; ++i) {
            StringBuilder newS = new StringBuilder(stringVec[i]);
//            int len = stringVec[i].length();

            if (!vowels.contains(stringVec[i].charAt(0))) {
                newS.append(newS.charAt(0));
                newS = new StringBuilder(newS.substring(1));
            }
            newS.append("ma");

            for (int j = 0; j < i + 1; ++j) {
                newS.append("a");
            }

//            while (len-- > 0) newS.append("a");

            if (i < stringVec.length - 1) newS.append(" ");
            ret.append(newS);
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(toGoatLatin("hihihi sdfsdf ahahah"));
    }
}
