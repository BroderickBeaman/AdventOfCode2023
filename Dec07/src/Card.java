public enum Card implements Comparable<Card> {
    JOKER("J", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    QUEEN("Q", 11),
    KING("K", 12),
    ACE("A", 13);



    private final String label;
    private final Integer value;

    Card(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public static Card fromLabel(String label) {
        for (Card card : values()) {
            if (card.label.equals(label)) {
                return card;
            }
        }
        return null;
    }
}
