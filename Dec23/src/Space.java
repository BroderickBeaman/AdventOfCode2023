public enum Space {
    WALL(false),
    EMPTY(false),
    EAST_SLOPE(true),
    WEST_SLOPE(true),
    NORTH_SLOPE(true),
    SOUTH_SLOPE(true);

    private final boolean slope;

    Space(boolean slope) {
        this.slope = slope;
    }

    public boolean isSlope() {
        return slope;
    }

    public Direction nextDirection() {
        return switch (this) {
            case EAST_SLOPE -> Direction.EAST;
            case WEST_SLOPE -> Direction.WEST;
            case NORTH_SLOPE -> Direction.NORTH;
            case SOUTH_SLOPE -> Direction.SOUTH;
            default -> throw new RuntimeException("Not a slope");
        };
    }

    public static Space fromChar(Character charIn) {
        return switch(charIn) {
            case '#' -> WALL;
            case '.' -> EMPTY;
            case '>' -> EAST_SLOPE;
            case '<' -> WEST_SLOPE;
            case '^' -> NORTH_SLOPE;
            case 'v' -> SOUTH_SLOPE;
            default -> throw new IllegalArgumentException("Not a valid space: " + charIn);
        };
    }
}
