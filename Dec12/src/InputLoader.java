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

        assert allLines != null;
        return allLines.stream().map(lineString -> {
            String springsString = lineString.split(" ")[0];
            String groupString = lineString.split(" ")[1];

            String expandedSpringString = String.join("?", List.of(springsString,springsString,springsString,springsString,springsString));
            String expandedGroupString = String.join(",", List.of(groupString,groupString,groupString,groupString,groupString));


            List<Spring> springs = new ArrayList<>();

            for (Character character : expandedSpringString.toCharArray()) {
                springs.add(Spring.fromChar(character));
            }

            List<Integer> groups = Arrays.stream(expandedGroupString.split(",")).map(Integer::parseInt).toList();

            return new Line(springs, groups);
        }).toList();
    }
}
