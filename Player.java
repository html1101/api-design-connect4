/**
 * Available players may be:
 * - Red
 * - Blue
 * 
 * The Player enum also supports an {@link opposite} method that allows 
 * getting the opposite of the given player.
 */
public enum Player {
    RED,
    BLUE;

    /**
     * Get the opposite of the current player.
     * 
     * @return red if player is blue, blue if player is red.
     */
    public Player opposite() {
        return this == RED ? BLUE : RED;
    }
}
