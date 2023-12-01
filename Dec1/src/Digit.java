import java.util.HashMap;
import java.util.Map;

public enum Digit {

    ONE("one", 1),
    TWO("two", 2),
    THREE("three", 3),
    FOUR("four", 4),
    FIVE("five", 5),
    SIX("six", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9);

    public final String label;
    public final int value;

    Digit(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public static String replaceDigits(String input) {
        for(Digit digit : values()) {
            input = input.replaceAll(digit.label, String.valueOf(digit.getValue()));
        }
        return input;
    }

    public static Map<Integer, Integer> extractDigitMap(String input) {
        Map<Integer, Integer> digitMap = new HashMap<>();
        for(Digit digit : values()) {
            int indexOfDigit = input.indexOf(digit.getLabel());
            if (indexOfDigit == -1) {
                continue;
            }
            digitMap.put(indexOfDigit, digit.getValue());
            int lastIndexOfDigit = input.lastIndexOf(digit.getLabel());
            digitMap.put(lastIndexOfDigit, digit.getValue());
        }
        return digitMap;
    }
}
