import java.util.List;

public class InputLoader extends InputLoaderParent {
    public static Space[][] loadPuzzleGrid() {
        List<String> allLines = loadLines();

        int height = allLines.size();
        int width = allLines.get(0).length();

        Space[][] puzzleGrid = new Space[height][width];

        for (int y = 0; y < height; y++) {
            String line = allLines.get(y);
            for (int x = 0; x < width; x++) {
                puzzleGrid[y][x] = Space.fromCharacter(line.charAt(x));
            }
        }

        return puzzleGrid;
    }
}
