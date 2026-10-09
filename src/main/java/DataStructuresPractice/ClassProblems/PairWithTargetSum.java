package DataStructuresPractice.ClassProblems;

import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSum {

    // Approach 1: brute force - O(n^2) time, O(1) space
    static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target) return true;
        return false;
    }

    // Approach 2: hash set - O(n) time, O(n) space
    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (seen.contains(target - n)) return true;
            seen.add(n);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasPairWithSum(new int[]{2, 7, 11, 15}, 9)); // true
        System.out.println(hasPairWithSum(new int[]{3, 4, 6}, 20));     // false
        System.out.println(bruteForce(new int[]{2, 7, 11, 15}, 9));     // true
    }
}