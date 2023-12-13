import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {

    public static List<Character[][]> loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Character[][]> puzzleList = new ArrayList<>();

        while (!allLines.isEmpty()) {
            int i = 0;
            // Find empty row
            while (true) {
                String line = allLines.get(i);
                if (line.trim().isEmpty()) {
                    break;
                }
                i++;
            }

            puzzleList.add(extractPuzzleGrid(allLines.subList(0, i)));
            allLines = allLines.subList(i + 1, allLines.size());
        }

        return puzzleList;
    }

    private static Character[][] extractPuzzleGrid(List<String> lines) {
        int height = lines.size();
        int width = lines.get(0).length();

        Character[][] puzzleGrid = new Character[height][width];

        for (int i = 0; i < height; i++) {
            char[] line = lines.get(i).toCharArray();
            for (int j = 0; j < width; j++) {
                puzzleGrid[i][j] = line[j];
            }
        }

        return puzzleGrid;
    }
}
