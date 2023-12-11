import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputLoader {

    public static InterstellarObject[][] loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int height = allLines.size();
        int width = allLines.get(0).length();

        InterstellarObject[][] puzzleGrid = new InterstellarObject[height][width];

        for (int i = 0; i < height; i++) {
            char[] line = allLines.get(i).toCharArray();
            for (int j = 0; j < width; j++) {
                puzzleGrid[i][j] = InterstellarObject.fromCharacter(line[j]);
            }
        }

        return puzzleGrid;
    }
}
