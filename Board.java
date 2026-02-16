/**
 * A Board from the Connect 4 game and its publicly available methods.
 * 
 * The game has 6 rows and 7 columns, and a client 
 * may access the number of rows, columns, the status of a specific 
 * cell, and whether a particular column or the entire board 
 * is full.
 */
public interface Board {
    int ROWS = 6;
    int COLUMNS = 7;

    /**
     * Get the number of rows in a board instance.
     * 
     * @return number of rows
     */
    int getRows();

    /**
     * Get the number of columns in a board instance.
     * 
     * @return number of columns
     */
    int getColumns();

    /**
     * Get the status of a current cell.
     * 
     * @param row zero-indexed row position
     * @param col zero-indexed column position
     */
    Player getCell(int row, int col);

    /**
     * Return if a particular column in the board is full.
     * 
     * @param col the (zero-indexed) index of the column.
     * 
     * @return true if board column is full, false otherwise
     */
    boolean isColumnFull(int col);
    
    /**
     * Return if board is full and there are no more 
     * possible moves to make.
     * 
     * @return true if board is full of tokens, false otherwise.
     */
    boolean isBoardFull();
}
