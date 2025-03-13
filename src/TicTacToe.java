import java.util.Scanner;

public class TicTacToe {

	// The game board
	private static char[][] board = new char[3][3];
	private static char currentPlayer = 'X'; // Player X starts

	public static void main(String[] args) {
		// Initialize the game board and scanner
		initializeBoard();
		Scanner scanner = new Scanner(System.in);

		// Print the initial game board
		printBoard(board);

		boolean gameEnded = false;

		while (!gameEnded) {
			// Prompt the current player for their move
			System.out.println("Player " + currentPlayer + "'s turn.");
			System.out.print("Enter row (0, 1, 2): ");
			int row = scanner.nextInt();
			System.out.print("Enter column (0, 1, 2): ");
			int col = scanner.nextInt();

			// Update the board with the player's move
			if (isValid(row, col)) {
				board[row][col] = currentPlayer;
				printBoard(board);

				// Check if the game has ended
				if (checkBoard(board, row, col)) {
					System.out.println("Player " + currentPlayer + " wins!");
					gameEnded = true;
				} else if (isBoardFull(board)) {
					System.out.println("The game is a draw!");
					gameEnded = true;
				} else {
					// Switch player
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move, try again.");
			}
		}

		scanner.close(); // Close scanner
	}

	/**
	 * Initializes the game board with empty spaces.
	 */
	public static void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' '; // Empty space
			}
		}
	}

	/**
	 * Prints the current state of the game board.
	 *
	 * @param board The game board.
	 */
	public static void printBoard(char[][] board) {
		System.out.println("Current board:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
				if (j < 2) System.out.print(" | ");
			}
			System.out.println();
			if (i < 2) System.out.println("---------");
		}
	}

	/**
	 * Checks if the current player has won the game.
	 *
	 * @param board The game board.
	 * @param row   The row of the last move.
	 * @param col   The column of the last move.
	 * @return True if the current player has won, false otherwise.
	 */
	public static boolean checkBoard(char[][] board, int row, int col) {
		// Check row
		if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
			return true;
		}

		// Check column
		if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
			return true;
		}

		// Check diagonals
		if (row == col) {
			if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
				return true;
			}
		}
		if (row + col == 2) {
			if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Validates if the given row and column are within the valid range.
	 *
	 * @param row The row number.
	 * @param col The column number.
	 * @return True if the row and column are valid, false otherwise.
	 */
	public static boolean isValid(int row, int col) {
		// Check if the row and column are within range
		if (row < 0 || row >= 3 || col < 0 || col >= 3) {
			return false;
		}

		// Check if the spot is already taken
		return board[row][col] == ' ';
	}

	/**
	 * Checks if the board is full (i.e., no empty spaces left).
	 *
	 * @param board The game board.
	 * @return True if the board is full, false otherwise.
	 */
	public static boolean isBoardFull(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false; // There's still an empty space
				}
			}
		}
		return true; // No empty space left, the board is full
	}
}
