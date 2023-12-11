public enum InterstellarObject {
    GALAXY,
    EMPTY_SPACE;

    public static InterstellarObject fromCharacter(Character charIn) {
        return switch (charIn) {
            case '.' -> EMPTY_SPACE;
            case '#' -> GALAXY;
            default -> null;
        };
    }
}
