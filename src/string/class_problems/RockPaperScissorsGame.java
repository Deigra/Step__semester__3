package string.class_problems;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {

    // Decides the winner of a single round
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
                (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
                (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int rounds = 5;
        int wins = 0, losses = 0, draws = 0;

        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        for (int i = 0; i < rounds; i++) {
            String playerMove;

            // Keep asking until the player enters a valid move
            while (true) {
                System.out.print("Round " + (i + 1) + " - Enter your move (Rock/Paper/Scissors): ");
                playerMove = scanner.nextLine().trim();

                if (playerMove.equalsIgnoreCase("Rock") ||
                        playerMove.equalsIgnoreCase("Paper") ||
                        playerMove.equalsIgnoreCase("Scissors")) {
                    break;
                }
                System.out.println("Invalid move. Please type Rock, Paper, or Scissors.");
            }

            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            System.out.println("Computer played: " + computerMove + " -> " + result);
            System.out.println();

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        // Print summary table
        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++) {
            System.out.println((i + 1) + "     | " + playerMoves[i] + "       | " + computerMoves[i] + "        | " + results[i]);
        }

        double winPercentage = (wins / (double) rounds) * 100;

        System.out.println();
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws + " | Win % = " + winPercentage + "%");

        scanner.close();
    }
}