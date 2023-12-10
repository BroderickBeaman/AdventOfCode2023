import java.util.List;

public enum Pipe {
    DOT(null),
    START(null),
    VERTICAL(List.of(Direction.NORTH, Direction.SOUTH)),
    HORIZONTAL(List.of(Direction.EAST, Direction.WEST)),
    NORTHWEST(List.of(Direction.NORTH, Direction.WEST)),
    NORTHEAST(List.of(Direction.NORTH, Direction.EAST)),
    SOUTHWEST(List.of(Direction.SOUTH, Direction.WEST)),
    SOUTHEAST(List.of(Direction.SOUTH, Direction.EAST));

    List<Direction> directions;

    Pipe(List<Direction> directions) {
        this.directions = directions;
    }

    public static Pipe fromCharacter(Character charIn) {
        return switch (charIn) {
            case '.' -> DOT;
            case 'S' -> START;
            case '|' -> VERTICAL;
            case '-' -> HORIZONTAL;
            case 'L' -> NORTHEAST;
            case 'J' -> NORTHWEST;
            case '7' -> SOUTHWEST;
            case 'F' -> SOUTHEAST;
            default -> null;
        };
    }

    public Direction nextDirection(Direction from) {
        if (this.directions == null) {
            return null;
        }

        return from.equals(this.directions.get(0)) ? this.directions.get(1) : this.directions.get(0);
    }
}
