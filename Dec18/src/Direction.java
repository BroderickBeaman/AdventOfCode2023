public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT(0, -1);

    public final int col;
    public final int row;

    Direction(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public static Direction fromChar(Character charIn) {
        return switch (charIn) {
            case 'U' -> UP;
            case 'D' -> DOWN;
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> null;
        };
    }

    public static Direction fromHexChar(Character charIn) {
        return switch (charIn) {
            case '0' -> RIGHT;
            case '1' -> DOWN;
            case '2' -> LEFT;
            case '3' -> UP;
            default -> null;
        };
    }
}
