/**
 * A game's current status may be one of the following:
 * - In progress - players are currently still playing,
 *   and there are still available empty slots to place
 *   checkers.
 * - Red win - The red checker player wins
 * - Black win - The black checker player wins
 * - Draw - Both players reach a draw
 */
public enum GameStatus {
    IN_PROGRESS,
    RED_WIN,
    BLUE_WIN,
    DRAW
}