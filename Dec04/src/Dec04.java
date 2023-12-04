import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dec04 {

    public static void main(String[] args) {

        List<Card> cards = CardLoader.loadCards();

        part2(cards);
    }

    private static void part2(List<Card> cards) {
        Map<Integer, Integer> cardToNumMatches = cards.stream()
                .collect(Collectors.toMap(Card::getCardNumber, Card::getNumMatches));

        Map<Integer, Integer> cardToNumCards = new HashMap<>();

        for (int i = cards.size() - 1; i >= 0; i--) {
            Integer numMatches = cardToNumMatches.get(i);
            int sumCards = 0;
            for (int j = 1; j <= numMatches; j++) {
                Integer numCopies = cardToNumCards.get(i + j);
                if (numCopies != null) {
                    sumCards += numCopies;
                }
            }

            cardToNumCards.put(i, sumCards + numMatches);
        }

        int sum = cardToNumCards.values().stream().mapToInt(Integer::intValue).sum() + cards.size();

        System.out.println("Total number of cards: " + sum);
    }

    private static void part1(List<Card> cards) {
        int sum = cards.stream().mapToInt(Card::getScore).sum();

        System.out.println("Score total: " + sum);
    }
}
