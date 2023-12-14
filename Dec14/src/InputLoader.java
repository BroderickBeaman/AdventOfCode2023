import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Character[][] loadPuzzleGrid() {
        List<String> allLines = loadLines();

        int height = allLines.size();
        int width = allLines.get(0).length();

        Character[][] puzzleGrid = new Character[height][width];

        for (int y = 0; y < height; y++) {
            String line = allLines.get(y);
            for (int x = 0; x < width; x++) {
                puzzleGrid[y][x] = line.charAt(x);
            }
        }

        return puzzleGrid;
    }
}
