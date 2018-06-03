import javafx.util.Pair;

import java.util.*;
import java.util.Map.Entry;

import static java.lang.Math.max;

// test case
//3
//4
//1 2 3 4
//8
//5 4 3 2 1 6 7 8
//8
//5 6 7 8 1 2 3 4

// results
//4
//4
//4


public class ALGOSPOT_LIS {

    static int N;
    static int seq[];
    static int memo[];

    static int dp(int pos) {
        int ret = memo[pos];
        if (ret != -1) return ret;

        ret = 0;
        for (int i = pos + 1; i <= N; ++i) {
            if (seq[pos] < seq[i]) ret = max(ret, dp(i) + 1);
        }
        return memo[pos] = ret;
    }

    static void driver() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; ++i) {
            N = sc.nextInt();
            seq = new int[N + 1];
            memo = new int[N + 1];
            Arrays.fill(memo, -1);

            seq[0] = 0;
            for (int j = 1; j <= N; ++j) seq[j] = sc.nextInt();

            System.out.println(dp(0));
        }
    }

    static void test() {
        HashMap<String, Integer> hash = new HashMap<>();
        hash.put("kim", 30);
        hash.put("jh", 20);
        hash.put("song", 40);



        // functional.
        hash.entrySet().stream()
                .sorted((k1, k2)-> -k1.getValue().compareTo(k2.getValue()))
                .forEach(k -> System.out.println(k.getKey() + " : " + k.getValue()));

//        List<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>(hash.keySet());
//        List<Map.Entry<String, Integer>> list = new ArrayList<>(hash.entrySet());
//        Collections.sort(list, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)->
//            o1.getValue().equals(o2.getValue()) ? 0 : o1.getValue() < o2.getValue() ? -1 : 1);

//                new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                int left = o1.getValue(), right = o2.getValue();
//                if (left == right) return 0;
//                return left < right ? 1 : -1;
//            }
//        });


//        Collections.sort(hash, new Comparator<Object>() {
//
//            @Override
//            public int compare(Object o1, Object o2) {
//                return 0;
//            }
//        });

//        Iterator<Map.Entry<String, Integer>> keys = list.iterator();
//        while (keys.hasNext()) {
//            Map.Entry<String, Integer> obj = keys.next();
//            System.out.println("key : " + obj.getKey() + " value : " + obj.getValue());
////            System.out.println("key : " + key + " value : " + hash.get(key));
//        }
    }

    public static void main(String[] args) {
//        driver();
        test();
    }
}
