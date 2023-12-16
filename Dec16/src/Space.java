import java.util.List;

public enum Space {

    EMPTY('.'),
    HORZ_SPLITTER('-'),
    VERT_SPLITTER('|'),
    FORWARD_MIRROR('/'),
    BACKWARD_MIRROR('\\');

    private Character label;

    Space(Character label) {
        this.label = label;
    }

    public boolean isMirror() {
        return this.equals(FORWARD_MIRROR) || this.equals(BACKWARD_MIRROR);
    }

    public boolean isSplitter() {
        return this.equals(HORZ_SPLITTER) || this.equals(VERT_SPLITTER);
    }

    public List<Direction> nextDirection(Direction currentDirection) {
        // Need two new directions
        if (isSplitter()) {
            return switch (currentDirection) {
                case UP, DOWN -> this.equals(VERT_SPLITTER) ? List.of(currentDirection) : List.of(Direction.LEFT, Direction.RIGHT);
                case LEFT, RIGHT -> this.equals(HORZ_SPLITTER) ? List.of(currentDirection) : List.of(Direction.UP, Direction.DOWN);
            };
        }

        if (isMirror()) {
            return switch (currentDirection) {
                case UP -> List.of(this.equals(FORWARD_MIRROR) ? Direction.RIGHT : Direction.LEFT);
                case DOWN -> List.of(this.equals(FORWARD_MIRROR) ? Direction.LEFT : Direction.RIGHT);
                case LEFT -> List.of(this.equals(FORWARD_MIRROR) ? Direction.DOWN : Direction.UP);
                case RIGHT -> List.of(this.equals(FORWARD_MIRROR) ? Direction.UP : Direction.DOWN);
            };
        }

        // If the space is empty, continue in the current direction
        return List.of(currentDirection);
    }

    public static Space fromCharacter(Character charIn) {
        for (Space space : values()) {
            if (space.label.equals(charIn)) {
                return space;
            }
        }
        return null;
    }
}
