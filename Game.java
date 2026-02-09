public interface Game {
    /**
     * Get the current player available.
     * 
     * @return the player whose current turn it is
     */
    Player getCurrentPlayer();

    /**
     * Get the current game status.
     * 
     * @return game status (in progress, red or blue win, or draw)
     */
    GameStatus getGameStatus();

    /**
     * Get the game board.
     * 
     * See {@link Board} for available methods.
     * 
     * @return game board
     */
    Board getBoard();

    /**
     * Drop a checker with the selected player, if possible,
     * at the selected column.
     * 
     * The current player can be found with {@link getCurrentPlayer}.
     * 
     * If unable to make a move (ex. an invalid column was passed in), 
     * the current player remains the same.
     * 
     * If they successfully make a move, the board, game status, 
     * and current player is updated.
     *
     * @param column zero-indexed, the column (by default 0-6).
     * @return true if making the move was successful, false otherwise
     */
    boolean dropChecker(int column);

    /**
     * Reset the entire board from scratch.
     * 
     * Set the entire {@link Board} to be completely empty, 
     * for it to be the red checker's turn, and for the game 
     * to be started over.
     */
    void reset();
}
