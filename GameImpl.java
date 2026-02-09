public final class GameImpl implements Game {

    private final BoardImpl board;
    private Player currentPlayer;
    private GameStatus status;

    public GameImpl() {
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
    public boolean makeMove(int column) {
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
