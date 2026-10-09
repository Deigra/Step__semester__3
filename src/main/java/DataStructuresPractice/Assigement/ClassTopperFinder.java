package DataStructuresPractice.Assigement;

public class ClassTopperFinder {

    static String findTopper(int[][] marks) {
        int bestRow = 0, bestTotal = -1;
        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int m : marks[i]) total += m;
            if (total > bestTotal) { bestTotal = total; bestRow = i; } // strict > keeps smallest index
        }
        return "(" + bestRow + ", " + bestTotal + ")";
    }

    public static void main(String[] args) {
        int[][] marks = {{78, 85, 90}, {88, 92, 79}, {65, 70, 95}};
        System.out.println(findTopper(marks)); // (1, 259)
    }
}
// Time: O(m * n), Space: O(1)