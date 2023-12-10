import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputLoader {
    public static Pipe[][] loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int height = allLines.size();
        int width = allLines.get(0).length();

        Pipe[][] puzzleGrid = new Pipe[height][width];

        for (int i = 0; i < height; i++) {
            String line = allLines.get(i);
            char[] lineCharacters = line.toCharArray();
            for (int j = 0; j < width; j++) {
                puzzleGrid[i][j] = Pipe.fromCharacter(lineCharacters[j]);
            }
        }

        return puzzleGrid;
    }
}
