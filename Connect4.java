/**
 * A Game of Connect 4 and its publicly available methods.
 * 
 * In the game, a client may access the current player,
 * game status, the board (readonly), and may
 * drop a token or reset the board.
 */
public interface Connect4 {
    /**
     * Get the current player available.
     * 
     * @return the player whose current turn it is
     */
    Player getCurrentPlayer();

    /**
     * Get the current game status.
     * 
     * @return game status (in progress, player A or B win, or draw)
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
     * Drop a token for the current player, if possible,
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
     * @return SUCCESS if making the move was successful, COLUMN_FULL if the
     *  column the user tried to drop a token down was already full,
     *  or INVALID_COLUMN if the column supplied was invalid.
     */
    MoveResult dropToken(int column);

    /**
     * Reset the entire board from scratch.
     * 
     * Set the entire {@link Board} to be completely empty,
     * for it to be the PLAYER_1 token's turn, and for the game
     * to be started over.
     */
    void reset();
}
