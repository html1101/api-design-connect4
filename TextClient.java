
/**
 * Sample code on how to interact with the Connect 4 API.
 * 
 * This is an example with two real game players,
 * who make their moves in a CLI.
 */

import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        // Create a new game to start.
        Connect4 game = new Connect4Impl();

        // Begin listening to STDIN for game input
        try (Scanner scanner = new Scanner(System.in)) {
            // While the game is not finished:
            while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
                // Print out the board in ASCII
                printBoard(game.getBoard());

                // Get whose turn it is, and ask them to choose a column to put their token.
                System.out.print(game.getCurrentPlayer() + " choose a column (0-6): ");

                // Get the column the player chooses
                int column = scanner.nextInt();

                // Drop a token at that particular column.
                MoveResult tokenResult = game.dropToken(column);
                if (!tokenResult.isSuccess()) {
                    // If unable to drop that token, print out an error and let them try again.
                    System.out.println("Supplied an invalid move! Error: " + tokenResult);
                }
            }

            // Print the board state out for the last time
            printBoard(game.getBoard());
            // Say who won the game
            System.out.println("Game over: " + game.getGameStatus() + "!");
        }
    }

    private static void printBoard(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getColumns(); c++) {
                Player p = board.getCell(r, c);
                System.out.print(p == null ? ". " : (p == Player.PLAYER_1 ? "1 " : "2 "));
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6\n");
    }
}
