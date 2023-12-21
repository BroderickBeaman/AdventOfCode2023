public enum Space {
    EMPTY,
    WALL,
    START;

    public static Space fromChar(Character charIn) {
        return switch (charIn) {
            case '.' -> EMPTY;
            case '#' -> WALL;
            case 'S' -> START;
            default -> throw new IllegalArgumentException("Not a valid space");
        };
    }
}
