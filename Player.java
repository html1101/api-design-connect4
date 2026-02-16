/**
 * Available players may be:
 * - PLAYER_1
 * - PLAYER_2
 * 
 * The Player enum also supports an {@link opponent} method that allows
 * getting the opponent of the given player.
 */
public enum Player {
    PLAYER_1 ("Player 1"),
    PLAYER_2 ("Player 2");

    /**
     * Get the opponent of the current player.
     * 
     * @return PLAYER_1 if the second player, PLAYER_2 if first player.
     */
    public Player opponent() {
        return this == PLAYER_1 ? PLAYER_2 : PLAYER_1;
    }

    // Hold a string to make our enums human-readable
    // as a string
    // Source: https://stackoverflow.com/questions/2497521/implementing-tostring-on-java-enums
    private final String humanReadableString;

    Player(String name) { humanReadableString = name; }

    @Override
    public String toString() {
        return humanReadableString;
    }
}
