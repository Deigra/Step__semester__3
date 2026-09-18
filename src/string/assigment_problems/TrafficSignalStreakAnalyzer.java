package string.assigment_problems;

import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {

    // Scans the signal log and finds the longest streak of the same character
    public static void findLongestStreak(String signalLog) {
        char longestChar = signalLog.charAt(0);
        int longestLength = 1;

        char currentChar = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLength++;
            } else {
                currentChar = signalLog.charAt(i);
                currentLength = 1;
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter signal log (e.g., RRGGGYRR): ");
        String signalLog = scanner.nextLine().trim();

        findLongestStreak(signalLog);

        scanner.close();
    }
}