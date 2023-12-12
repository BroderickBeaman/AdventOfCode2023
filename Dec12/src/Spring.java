public enum Spring {
    UNKNOWN('?'),
    WORKING('.'),
    BROKEN('#');

    private Character character;

    Spring(Character character) {
        this.character = character;
    }

    public static Spring fromChar(Character charIn) {
        for (Spring spring : values()) {
            if (spring.character.equals(charIn)) {
                return spring;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
