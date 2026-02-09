public enum Player {
    RED,
    BLUE;

    public Player opposite() {
        return this == RED ? BLUE : RED;
    }
}
