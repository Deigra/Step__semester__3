package string.assigment_problems;

import java.util.Scanner;

public class MovieReviewWordProfiler {

    // Classifies each word in the review as Short, Medium, or Long
    public static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");

        int shortCount = 0, mediumCount = 0, longCount = 0;

        for (String word : words) {
            int length = word.length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else {
                longCount++; // 9+ letters
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a movie review: ");
        String review = scanner.nextLine();

        classifyWordLengths(review);

        scanner.close();
    }
}