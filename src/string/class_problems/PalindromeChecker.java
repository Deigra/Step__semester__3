package string.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    // Approach 1: Iterative - compare characters from both ends moving inward
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Recursive - compare first and last characters, shrink each call
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        // Check the substring without the first and last characters
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: Array reversal - reverse the character array and compare
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase to check: ");
        String input = scanner.nextLine().trim().toLowerCase();

        // Remove spaces so phrases like "nurses run" could also be checked if needed
        String cleanedInput = input.replaceAll("\\s+", "");

        boolean iterativeResult = isPalindromeIterative(cleanedInput);
        boolean recursiveResult = isPalindromeRecursive(cleanedInput);
        boolean arrayReversalResult = isPalindromeArrayReversal(cleanedInput);

        System.out.println("Iterative: " + (iterativeResult ? "Palindrome" : "Not Palindrome"));
        System.out.println("Recursive: " + (recursiveResult ? "Palindrome" : "Not Palindrome"));
        System.out.println("Array Reversal: " + (arrayReversalResult ? "Palindrome" : "Not Palindrome"));

        scanner.close();
    }
}