import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Space[][] loadPuzzleGrid() {
        List<String> allLines = loadLines();

        int height = allLines.size();
        int width = allLines.get(0).length();

        Space[][] puzzleGrid = new Space[height][width];

        for (int row = 0; row < height; row++) {
            String line = allLines.get(row);
            for (int col = 0; col < width; col++) {
                puzzleGrid[row][col] = Space.fromChar(line.charAt(col));
            }
        }

        return puzzleGrid;
    }
}
