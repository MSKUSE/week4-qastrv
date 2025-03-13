import java.util.Scanner;

public class Nim {
    /**
     * The main method to run the Nim game.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] piles = {3, 4, 5}; // Example piles with 3, 4, and 5 stones
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        int currentPlayer = 1; // Player 1 starts

        // Main game loop
        while (!gameOver) {
            printPiles(piles); // Print the piles
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Choose a pile (0, 1, 2): ");
            int pile = scanner.nextInt();
            System.out.print("Enter number of stones to remove: ");
            int stones = scanner.nextInt();

            // Validate the move
            if (isValidMove(piles, pile, stones)) {
                // Update the chosen pile
                piles[pile] -= stones;

                // Check if the game is over
                if (isGameOver(piles)) {
                    gameOver = true;
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    // Switch to the next player
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        scanner.close(); // Close the scanner
    }

    /**
     * Prints the current state of the piles with stars representing the stones.
     *
     * @param piles The array representing the number of stones in each pile.
     */
    public static void printPiles(int[] piles) {
        for (int i = 0; i < piles.length; i++) {
            System.out.print("Pile " + i + ": ");
            for (int j = 0; j < piles[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the game is over by verifying if all piles are empty.
     *
     * @param piles The array representing the number of stones in each pile.
     * @return True if all piles are empty, false otherwise.
     */
    public static boolean isGameOver(int[] piles) {
        // If all piles are empty, the game is over
        for (int pile : piles) {
            if (pile > 0) {
                return false; // At least one pile has stones, so the game isn't over
            }
        }
        return true; // All piles are empty, the game is over
    }

    /**
     * Validates the player's move.
     *
     * @param piles The array representing the number of stones in each pile.
     * @param pile The pile chosen by the player.
     * @param stones The number of stones the player wants to remove.
     * @return True if the move is valid, false otherwise.
     */
    public static boolean isValidMove(int[] piles, int pile, int stones) {
        // Check if the pile is valid (within bounds)
        if (pile < 0 || pile >= piles.length) {
            return false;
        }

        // Check if the number of stones is valid (greater than 0 and not more than the stones in the chosen pile)
        if (stones <= 0 || stones > piles[pile]) {
            return false;
        }

        return true; // The move is valid
    }
}
