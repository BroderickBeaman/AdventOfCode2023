import java.util.HashSet;
import java.util.Set;

public class Card {

    Integer cardNumber;

    private Set<Integer> winningNumbers;

    private Set<Integer> cardNumbers;

    public Card(Integer cardNumber, Set<Integer> winningNumbers, Set<Integer> cardNumbers) {
        this.cardNumber = cardNumber;
        this.winningNumbers = winningNumbers;
        this.cardNumbers = cardNumbers;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(Set<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Set<Integer> getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(Set<Integer> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public int getNumMatches() {
        Set<Integer> intersection = new HashSet<>(winningNumbers);
        intersection.retainAll(cardNumbers);

        return intersection.size();
    }

    public int getScore() {
        int numMatches = getNumMatches();
        if (numMatches > 0) {
            return (int) Math.pow(2, numMatches - 1);
        }

        return 0;
    }
}
