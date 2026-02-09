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
     * @param row the (zero-indexed) index of the column.
     * 
     * @return true if board column is full, false otherwise
     */
    boolean isColumnFull(int col);
    
    /**
     * Return if board is full and there are no more 
     * possible moves to make.
     * 
     * @return true if board is full of checkers, false otherwise.
     */
    boolean isBoardFull();
}
