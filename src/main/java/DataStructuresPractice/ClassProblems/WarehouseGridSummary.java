package DataStructuresPractice.ClassProblems;

public class WarehouseGridSummary {

    static String warehouseSummary(int[][] grid) {
        int total = 0, max = -1, r = 0, c = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
                if (grid[i][j] > max) { max = grid[i][j]; r = i; c = j; }
            }
        return "(" + total + ", (" + r + ", " + c + "))";
    }

    public static void main(String[] args) {
        int[][] grid = {{4, 9, 2}, {7, 1, 6}, {3, 12, 5}};
        System.out.println(warehouseSummary(grid)); // (49, (2, 1))
    }
}
// Time: O(m * n), Space: O(1)