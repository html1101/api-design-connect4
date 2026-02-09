import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        Game game = new GameImpl();
        try (Scanner scanner = new Scanner(System.in)) {
            while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
                printBoard(game.getBoard());
                
                System.out.print(game.getCurrentPlayer() + " choose a column (0-6): ");
                
                int column = scanner.nextInt();
                
                if (!game.makeMove(column)) {
                    System.out.println("Invalid move. Try again.");
                }
            }
            
            printBoard(game.getBoard());
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
