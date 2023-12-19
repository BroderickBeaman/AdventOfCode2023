public enum Comparison {
    GREATER_THAN,
    LESS_THAN;

    public static Comparison fromCharacter(Character charIn) {
        return switch (charIn) {
            case '>' -> GREATER_THAN;
            case '<' -> LESS_THAN;
            default -> null;
        };
    }
}
