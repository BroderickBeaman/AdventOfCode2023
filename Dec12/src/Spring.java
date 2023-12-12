public enum Spring {
    UNKNOWN,
    WORKING,
    BROKEN;

    public static Spring fromChar(Character charIn) {
        return switch (charIn) {
            case '?' -> UNKNOWN;
            case '.' -> WORKING;
            case '#' -> BROKEN;
            default -> null;
        };
    }
}
