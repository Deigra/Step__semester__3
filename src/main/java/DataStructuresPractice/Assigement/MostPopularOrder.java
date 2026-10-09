package DataStructuresPractice.Assigement;

import java.util.HashMap;
import java.util.Map;

public class MostPopularOrder {

    static String mostPopular(String[] orders) {
        Map<String, Integer> count = new HashMap<>();
        for (String o : orders) count.merge(o, 1, Integer::sum);

        String best = null;
        int bestCount = 0;
        for (String o : orders) {                 // original order breaks ties
            if (count.get(o) > bestCount) { bestCount = count.get(o); best = o; }
        }
        return "(\"" + best + "\", " + bestCount + ")";
    }

    public static void main(String[] args) {
        System.out.println(mostPopular(new String[]{"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"})); // ("dosa", 3)
        System.out.println(mostPopular(new String[]{"tea", "coffee", "coffee", "tea"}));                      // ("tea", 2)
    }
}
// Hash map: Time O(n), Space O(k) where k = distinct items
// Rescanning the list for every item: Time O(n^2), Space O(1)