import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class HandLoader {

    public static List<Hand> loadHands() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(line -> {
            String[] lineSplit = line.split(" ");
            String handString = lineSplit[0];
            String betString = lineSplit[1];

            List<Card> cards = Arrays.stream(handString.split("")).map(Card::fromLabel).toList();
            Integer bet = Integer.parseInt(betString);

            return new Hand(cards, bet);
        }).toList();
    }
}
