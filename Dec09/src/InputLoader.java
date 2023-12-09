import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InputLoader {

    public static List<List<Integer>> loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()).toList();
    }
}
