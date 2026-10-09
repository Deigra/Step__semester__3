package DataStructuresPractice.ClassProblems;

public class MaxContainerArea {

    // Brute force - O(n^2) time, O(1) space
    static int bruteForce(int[] h) {
        int max = 0;
        for (int i = 0; i < h.length; i++)
            for (int j = i + 1; j < h.length; j++)
                max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
        return max;
    }

    // Two pointers - O(n) time, O(1) space
    static int maxContainerArea(int[] h) {
        int left = 0, right = h.length - 1, max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(h[left], h[right]) * (right - left));
            if (h[left] < h[right]) left++;
            else right--;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(bruteForce(heights));       // 49
        System.out.println(maxContainerArea(heights)); // 49
    }
}