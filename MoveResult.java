/**
 * Potential status of trying to drop a token in the board.
 * 
 * A player may:
 * - Successfully drop a token (+ include row)
 * - Try to drop a token down an already full column
 * - Supply an invalid column
 * - Try to move when a game hasn't started
 */
enum MoveStatus {
    SUCCESS("success"),
    COLUMN_FULL("column full"),
    INVALID_COLUMN("invalid column"),
    NO_CURRENT_GAME("no current game running");


    // Hold a string to make our enums human-readable
    // as a string
    // Source: https://stackoverflow.com/questions/2497521/implementing-tostring-on-java-enums
    private final String humanReadableString;

    MoveStatus(String name) { humanReadableString = name; }

    @Override
    public String toString() {
        return humanReadableString;
    }
}

/**
 * Result of dropping a token in the board.
 * 
 * This allows us to additionally specify a row (-1 if unspecified) 
 * when we successfully drop a token.
 */
public record MoveResult (MoveStatus status, int row) {
    /**
     * Whether the player was able to successfully move.
     */
    public boolean isSuccess() {
        return status == MoveStatus.SUCCESS;
    }

    public static MoveResult columnFull() {
        return new MoveResult(MoveStatus.COLUMN_FULL, -1);
    }

    public static MoveResult invalidColumn() {
        return new MoveResult(MoveStatus.INVALID_COLUMN, -1);
    }

    public static MoveResult successfulMove(int row) {
        return new MoveResult(MoveStatus.SUCCESS, row);
    }

    public static MoveResult NoGameInProgress() {
        return new MoveResult(MoveStatus.NO_CURRENT_GAME, -1);
    }

    @Override
    public String toString() {
        return this.status().toString();
    }
}