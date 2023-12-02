public enum Colour {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String label;

    Colour(String label) {
        this.label = label;
    }

    public static Colour fromLabel(String label) {
        for (Colour colour : values()) {
            if (colour.label.equals(label)) {
                return colour;
            }
        }
        return null;
    }
}
