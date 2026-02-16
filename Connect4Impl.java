/**
 * An API to handle the game logic of Connect 4.
 * 
 * Connect 4 is a two-player game where each player tries to make a straight
 * line (vertical, horizontal, or diagonal) of four of their colored tokens by
 * dropping their tokens into a 6 x 7 grid. The two players take turns playing
 * until one of them wins, or the board is full.
 * 
 * See <a href="https://youtu.be/ylZBRUJi3UQ?si=8IzWBx4_vP8jgVAX">this video</a>
 * for a visual on how to play Connect 4.
 * 
 * The following is a simple example of how to create a basic ASCII
 * two-player Connect 4 CLI using this API:
 * 
 * {@snippet :
 * import java.util.Scanner;
 * 
 * public class TextClient {
 * 
 *     public static void main(String[] args) {
 *         // Create a new game to start.
 *         Game game = new Connect4();
 * 
 *         // Begin listening to STDIN for game input
 *         try (Scanner scanner = new Scanner(System.in)) {
 *             // While the game is not finished:
 *             while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
 *                 // Print out the board in ASCII
 *                 printBoard(game.getBoard());
 * 
 *                 // Get whose turn it is, and ask them to choose a column to put their token.
 *                 System.out.print(game.getCurrentPlayer() + " choose a column (0-6): ");
 * 
 *                 // Get the column the player chooses
 *                 int column = scanner.nextInt();
 * 
 *                 // Drop a token at that particular column.
 * 
 *                 MoveResult tokenResult = game.dropToken(column);
 *                 if (!tokenResult.isSuccess()) {
 *                     // If unable to drop that token, print out an error and let them try again.
 *                     System.out.println("Supplied an invalid move! Error: " + tokenResult);
 *                 }
 *             }
 * 
 *             // Print the board state out for the last time
 *             printBoard(game.getBoard());
 *             // Say who won the game
 *             System.out.println("Game over: " + game.getGameStatus());
 *         }
 *     }
 * 
 *     private static void printBoard(Board board) {
 *         for (int r = 0; r < board.getRows(); r++) {
 *             for (int c = 0; c < board.getColumns(); c++) {
 *                 Player p = board.getCell(r, c);
 *                 System.out.print(p == null ? ". " : (p == Player.PLAYER_1 ? "A " : "B "));
 *             }
 *             System.out.println();
 *         }
 *         System.out.println("0 1 2 3 4 5 6\n");
 *     }
 * }
 * }
 */
public final class Connect4Impl implements Connect4 {

    private final BoardImpl board;
    private Player currentPlayer;
    private GameStatus status;

    /**
     * Create a new, blank Connect 4 instance.
     * 
     * This will be a 6 x 7 grid that starts with player A
     * playing first.
     */
    public Connect4Impl() {
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
    public MoveResult dropToken(int column) {
        // If the game is not currently in progress, cannot drop a token
        if (status != GameStatus.IN_PROGRESS) {
            return MoveResult.NoGameInProgress();
        }

        MoveResult tokenResult = board.dropToken(column, currentPlayer);
        // If that doesn't work, return the error we received
        if (!tokenResult.isSuccess()) {
            return tokenResult;
        }

        // Get the row + check if this made a new win
        if (checkWin(tokenResult.row(), column)) {
            status = (currentPlayer == Player.PLAYER_1) ? GameStatus.PLAYER_1_WIN : GameStatus.PLAYER_2_WIN;
        } else if (board.isBoardFull()) {
            status = GameStatus.DRAW;
        } else {
            currentPlayer = currentPlayer.opponent();
        }

        return tokenResult;
    }

    @Override
    public void reset() {
        board.clear();
        currentPlayer = Player.PLAYER_1;
        status = GameStatus.IN_PROGRESS;
    }

    private boolean checkWin(int row, int col) {
        Player p = board.getCell(row, col);

        return count(row, col, 1, 0, p) >= 4 || // vertical
                count(row, col, 0, 1, p) >= 4 || // horizontal
                count(row, col, 1, 1, p) >= 4 || // diagonal \
                count(row, col, 1, -1, p) >= 4; // diagonal /
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
