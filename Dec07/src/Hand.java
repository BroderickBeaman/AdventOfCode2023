import java.util.*;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;
    private Integer bet;
    private HandType handType;



    public Hand(List<Card> cards, Integer bet) {
        this.cards = cards;
        this.bet = bet;
        setHandType();
    }

    private void setHandType() {
        handType = computeHandType(this.cards);

        if (this.cards.contains(Card.JOKER)) {
            Set<Card> uniqueCardsInHand = new HashSet<>(this.cards);
            uniqueCardsInHand.remove(Card.JOKER); // No need to replace JOKER with JOKER
            for (Card card : uniqueCardsInHand) {
                List<Card> tempCards = new ArrayList<>(this.cards);
                Collections.replaceAll(tempCards, Card.JOKER, card);
                HandType tempHandType = computeHandType(tempCards);
                if (tempHandType.compareTo(this.handType) > 0) {
                    this.handType = tempHandType;
                }
            }
        }
    }

    private HandType computeHandType(List<Card> handOfCards) {
        List<Integer> cardFrequencies = new ArrayList<>();
        for (Card card : Card.values()) {
            cardFrequencies.add(Collections.frequency(handOfCards, card));
        }

        if (cardFrequencies.contains(5)) {
            return HandType.FIVE_OF_A_KIND;
        }

        if (cardFrequencies.contains(4)) {
           return HandType.FOUR_OF_A_KIND;
        }

        if (cardFrequencies.contains(3)) {
            return cardFrequencies.contains(2) ? HandType.FULL_HOUSE : HandType.THREE_OF_A_KIND;
        }

        if (cardFrequencies.contains(2)) {
            return Collections.frequency(cardFrequencies, 2) >= 2 ? HandType.TWO_PAIR : HandType.ONE_PAIR;
        }

        return HandType.HIGH_CARD;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Integer getBet() {
        return bet;
    }


    @Override
    public int compareTo(Hand other) {
        int typeComparison = this.handType.compareTo(other.handType);
        if (typeComparison != 0) {
            return typeComparison;
        }

        for (int i = 0; i < cards.size(); i++) {
            int cardComparison = cards.get(i).compareTo(other.getCards().get(i));
            if (cardComparison != 0) {
                return cardComparison;
            }
        }

        return 0;
    }
}
