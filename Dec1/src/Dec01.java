import java.util.List;

public class Dec01 {

    public static void main(String[] args) {
        List<CalibrationValue> calibrationValues = CalibrationValueLoader.loadCalibrationValuesPart2();

        int sum = calibrationValues.stream().mapToInt(CalibrationValue::getCombinedValue).sum();

        System.out.println("Sum of values: " + sum);
    }
}
