/**
 * An implementation of the board's methods.
 */
public class BoardImpl implements Board {
    private final Player[][] grid;

    public BoardImpl() {
        grid = new Player[ROWS][COLUMNS];
    }

    @Override
    public int getRows() {
        return ROWS;
    }

    @Override
    public int getColumns() {
        return COLUMNS;
    }

    @Override
    public Player getCell(int row, int column) {
        return grid[row][column];
    }

    @Override
    public boolean isColumnFull(int column) {
        return grid[0][column] != null;
    }

    @Override
    public boolean isBoardFull() {
        for (int c = 0; c < COLUMNS; c++) {
            if (!isColumnFull(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Drop a checker on the board from a given player.
     * 
     * 
     * @param column zero-indexed, the column to drop a checker down.
     * @param player the player who is dropping the checker
     * 
     * @return -1 if columns are full or the inputted column is out of range.
     */
    int dropChecker(int column, Player player) {
        if (column < 0 || column >= COLUMNS || isColumnFull(column)) {
            return -1;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == null) {
                grid[row][column] = player;
                return row;
            }
        }
        return -1;
    }


    /**
     * Clear out the entire board.
     */
    void clear() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                grid[r][c] = null;
            }
        }
    }
}
