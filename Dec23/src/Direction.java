import java.util.ArrayList;
import java.util.List;

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

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    public List<Direction> allMinusOpposite() {
        List<Direction> directions = new ArrayList<>();
        for (Direction direction : values()) {
            if (this.opposite() != direction) {
                directions.add(direction);
            }
        }
        return directions;
    }
}
