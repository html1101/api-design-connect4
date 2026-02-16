/**
 * A game's current status may be one of the following:
 * - In progress - players are currently still playing,
 * and there are still available empty slots to place
 * tokens.
 * - Player 1 win - The PLAYER_1 token player wins
 * - Player 2 win - The PLAYER_2 token player wins
 * - Draw - Both players reach a draw
 */
public enum GameStatus {
    IN_PROGRESS ("game in progress"),
    PLAYER_1_WIN ("player 1 wins"),
    PLAYER_2_WIN ("player 2 wins"),
    DRAW ("the players draw");

    // Hold a string to make our enums human-readable
    // as a string
    // Source: https://stackoverflow.com/questions/2497521/implementing-tostring-on-java-enums
    private final String humanReadableString;

    GameStatus(String name) { humanReadableString = name; }

    @Override
    public String toString() {
        return humanReadableString;
    }

}
