import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CalibrationValueLoader {

    public static List<CalibrationValue> loadCalibrationValuesPart1() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CalibrationValue> calibrationValues = new ArrayList<>();
        return allLines.stream().map(rawInput -> {
            rawInput = Digit.replaceDigits(rawInput);
            int[] digits = rawInput.chars()
                    .filter(value -> value >= 48 && value <= 57)
                    .map(value -> value - 48)
                    .toArray();

            return new CalibrationValue(digits[0], digits[digits.length - 1]);
        }).toList();

    }

    public static List<CalibrationValue> loadCalibrationValuesPart2() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CalibrationValue> calibrationValues = new ArrayList<>();
        return allLines.stream().map(rawInput -> {
            Map<Integer, Integer> digitMap = Digit.extractDigitMap(rawInput);
            for (int i = 0; i < rawInput.length(); i++) {
                char character = rawInput.charAt(i);
                if (Character.isDigit(character)) {
                    digitMap.put(i, (int) character - 48);
                }
            }

            List<Integer> keys = new ArrayList<>(digitMap.keySet());
            Collections.sort(keys);
            return new CalibrationValue(digitMap.get(keys.get(0)), digitMap.get(keys.get(keys.size() - 1)));

        }).toList();

    }

}
