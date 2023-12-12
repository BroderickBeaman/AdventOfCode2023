import java.util.List;

public class Dec12 {

    public static void main(String[] args) {
        List<Line> inputList = InputLoader.loadInput();

        int sum = inputList.stream().mapToInt(Line::countPossibilities).sum();
        System.out.println("Number of possibilities: " + sum);
    }
}
