/**
 * An API to handle the game logic of Connect 4.
 * 
 * Connect 4 is a two-player game where each player tries to make a straight 
 * line (vertical, horizontal, or diagonal) of four of their colored checkers by 
 * dropping their checkers into a 6 x 7 grid. The two players take turns playing 
 * until one of them wins, or the board is full.
 * 
 * See <a href="https://youtu.be/ylZBRUJi3UQ?si=8IzWBx4_vP8jgVAX">this video</a> 
 * for a visual on how to play Connect 4.
 * 
 * The following is a simple example of how to create a basic ASCII 
 * two-player Connect 4 CLI using this API:
 * 
 * {@snippet :
import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        // Create a new game to start.
        Game game = new Connect4();
        
        // Begin listening to STDIN for game input
        try (Scanner scanner = new Scanner(System.in)) {
            // While the game is not finished:
            while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
                // Print out the board in ASCII
                printBoard(game.getBoard());
                
                // Get whose turn it is, and ask them to choose a column to put their checker.
                System.out.print(game.getCurrentPlayer() + " choose a column (0-6): ");
                
                // Get the column the player chooses
                int column = scanner.nextInt();
                
                // Drop a checker at that particular column.
                if (!game.dropChecker(column)) {
                    // If unable to drop that checker, print out an error and let them try again.
                    System.out.println("Invalid move. Try again.");
                }
            }
            
            // Print the board state out for the last time
            printBoard(game.getBoard());
            // Say who won the game
            System.out.println("Game over: " + game.getGameStatus());
        }
    }

    private static void printBoard(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getColumns(); c++) {
                Player p = board.getCell(r, c);
                System.out.print(p == null ? ". " : (p == Player.RED ? "R " : "B "));
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6\n");
    }
}
 * }
 */
public final class Connect4 implements Game {

    private final BoardImpl board;
    private Player currentPlayer;
    private GameStatus status;

    /**
     * Create a new, blank Connect 4 instance.
     * 
     * This will be a 6 x 7 grid that starts with the red player 
     * playing first.
     */
    public Connect4() {
        board = new BoardImpl();
        reset();
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean dropChecker(int column) {
        if (status != GameStatus.IN_PROGRESS) {
            return false;
        }

        int row = board.dropChecker(column, currentPlayer);
        if (row == -1) {
            return false;
        }

        if (checkWin(row, column)) {
            status = (currentPlayer == Player.RED) ? GameStatus.RED_WIN : GameStatus.BLUE_WIN;
        } else if (board.isBoardFull()) {
            status = GameStatus.DRAW;
        } else {
            currentPlayer = currentPlayer.opposite();
        }

        return true;
    }

    @Override
    public void reset() {
        board.clear();
        currentPlayer = Player.RED;
        status = GameStatus.IN_PROGRESS;
    }

    private boolean checkWin(int row, int col) {
        Player p = board.getCell(row, col);

        return count(row, col, 1, 0, p) >= 4 ||   // vertical
               count(row, col, 0, 1, p) >= 4 ||   // horizontal
               count(row, col, 1, 1, p) >= 4 ||   // diagonal \
               count(row, col, 1, -1, p) >= 4;    // diagonal /
    }

    private int count(int row, int col, int dRow, int dCol, Player p) {
        return 1 + countWithDir(row, col, dRow, dCol, p) + countWithDir(row, col, -dRow, -dCol, p);
    }

    private int countWithDir(int row, int col, int dirRow, int dirCol, Player p) {
        int r = row + dirRow;
        int c = col + dirCol;
        int count = 0;

        while (r >= 0 && r < Board.ROWS && c >= 0 && c < Board.COLUMNS && board.getCell(r, c) == p) {
            count++;
            r += dirRow;
            c += dirCol;
        }
        return count;
    }
}
