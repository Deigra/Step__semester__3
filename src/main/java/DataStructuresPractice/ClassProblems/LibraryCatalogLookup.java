package DataStructuresPractice.ClassProblems;

public class LibraryCatalogLookup {

    static String findBook(String[][] catalog, String target) {
        int low = 0, high = catalog.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = catalog[mid][0].compareTo(target);
            if (cmp == 0) return catalog[mid][1];
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
                {"0001112223", "Introduction to Algebra"},
                {"0002223334", "Beginning Python"},
                {"0003334445", "Classic Mythology"},
                {"0004445556", "Data and Society"},
                {"0005556667", "European History"}
        };
        System.out.println(findBook(catalog, "0003334445")); // Classic Mythology
        System.out.println(findBook(catalog, "0009998887")); // Not Found
    }
}
// Time: O(log n), Space: O(1)