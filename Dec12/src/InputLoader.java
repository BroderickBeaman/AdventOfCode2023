import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputLoader {

    public static List<Line> loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(lineString -> {
            String springsString = lineString.split(" ")[0];
            String groupString = lineString.split(" ")[1];

            List<Spring> springs = new ArrayList<>();

            for (Character character : springsString.toCharArray()) {
                springs.add(Spring.fromChar(character));
            }

            List<Integer> groups = Arrays.stream(groupString.split(",")).map(Integer::parseInt).toList();

            return new Line(springs, groups);
        }).toList();
    }
}
