import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CardLoader {

    public static List<Card> loadCards() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(line -> {
            Integer cardNumber = Integer.parseInt(line.split(": ")[0].split("\\s+")[1]) - 1;
            String numbers = line.split(": ")[1];

            String[] splitNumbers = numbers.split(" \\| ");

            String[] winningNumbersStrings = splitNumbers[0].split("\\s+");
            Set<Integer> winningNumbers = Arrays.stream(winningNumbersStrings)
                    .filter(string -> !string.isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());

            String[] cardNumbersStrings = splitNumbers[1].split("\\s+");
            Set<Integer> cardNumbers = Arrays.stream(cardNumbersStrings)
                    .filter(string -> !string.isEmpty())
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());

            return new Card(cardNumber, winningNumbers, cardNumbers);
        }).toList();
    }
}
