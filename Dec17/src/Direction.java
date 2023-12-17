import java.util.List;

public enum Direction {
    STILL(0, 0),
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    public final int col;
    public final int row;

    Direction(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
            case STILL -> null;
        };
    }

    public static List<Direction> moving() {
        return List.of(NORTH, SOUTH, EAST, WEST);
    }
}
