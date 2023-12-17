import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static Integer[][] loadPuzzleGrid() {
        List<String> allLines = loadLines();

        int height = allLines.size();
        int width = allLines.get(0).length();

        Integer[][] puzzleGrid = new Integer[height][width];
        for (int y = 0; y < height; y++) {
            String[] numberArray = allLines.get(y).split("");
            for (int x = 0; x < width; x++) {
                puzzleGrid[y][x] = Integer.parseInt(numberArray[x]);
            }
        }

        return puzzleGrid;
    }
}
