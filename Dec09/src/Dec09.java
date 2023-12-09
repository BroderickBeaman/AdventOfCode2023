import java.util.ArrayList;
import java.util.List;

public class Dec09 {

    public static void main(String[] args) {

        List<List<Integer>> inputList = InputLoader.loadInput();

        int sum = inputList.stream().mapToInt(Dec09::computeNextInSequence).sum();

        System.out.println("Sum: " + sum);
    }

    private static Integer computeNextInSequence(List<Integer> intList) {
        //Identity
        if (intList.stream().allMatch(integer -> integer == 0)) {
            return 0;
        }

        List<Integer> nextSequence = new ArrayList<>();
        for (int i = 1; i < intList.size(); i++) {
            nextSequence.add(intList.get(i) - intList.get(i - 1));
        }

        return intList.get(intList.size() - 1) + computeNextInSequence(nextSequence);
    }
}
