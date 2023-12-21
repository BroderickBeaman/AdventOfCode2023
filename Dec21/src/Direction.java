public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    public final int row;
    public final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
