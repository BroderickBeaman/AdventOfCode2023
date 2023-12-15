import java.util.List;

public class Dec15 {
    public static void main(String[] args) {
        List<String> puzzleInputs = InputLoader.loadInputs();
        long result = puzzleInputs.stream().mapToLong(Dec15::hashInput).sum();

        System.out.println("Sum of hashes: " + result);
    }

    public static long hashInput(String string) {
        long hash = 0;
        for (int i = 0; i < string.length(); i++) {
            hash += string.charAt(i);
            hash = hash * 17;
            hash = hash % 256;
        }
        return hash;
    }
}
