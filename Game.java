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
     * Drop a checker with the selected player.
     * 
     * The current player can be found with {@link getCurrentPlayer}.
     *
     * @param col zero-indexed, the column (by default 0-6).
     * @return true if making the move was successful, false otherwise
     */
    boolean makeMove(int col);

    /**
     * Reset the entire board.
     */
    void reset();
}
