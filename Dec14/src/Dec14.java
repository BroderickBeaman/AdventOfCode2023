import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dec14 {

    public static void main(String[] args) {
        Character[][] puzzleGrid = InputLoader.loadPuzzleGrid();

        Map<String, Long> puzzleStateToIteration = new HashMap<>();
        Map<Long, Long> iterationToLoad = new HashMap<>();

        long maxCycles = 1000000000L;
        long cycleStartsAt = 0;
        long cycleLength = 0;
        for (long i = 1; i <= maxCycles; i ++) {
            puzzleGrid = cycle(puzzleGrid);
            String puzzleString = computeString(puzzleGrid);
            if (puzzleStateToIteration.containsKey(puzzleString)) {
                cycleStartsAt = puzzleStateToIteration.get(puzzleString);
                cycleLength = i - cycleStartsAt;
                break;
            }
            puzzleStateToIteration.put(puzzleString, i);
            iterationToLoad.put(i, calculateLoad(puzzleGrid));
        }

        long indexOfLoad = ((maxCycles-cycleStartsAt) % cycleLength) + cycleStartsAt;

        System.out.println("Load is: " + iterationToLoad.get(indexOfLoad));

    }

    public static Character[][] cycle(Character[][] puzzleGrid) {
        puzzleGrid = tiltNorth(puzzleGrid);
        puzzleGrid = tiltWest(puzzleGrid);
        puzzleGrid = tiltSouth(puzzleGrid);
        puzzleGrid = tiltEast(puzzleGrid);
        return puzzleGrid;
    }

    public static long calculateLoad(Character[][] puzzleGrid) {
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;
        long load = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (puzzleGrid[y][x].equals('O')) {
                    load += height - y;
                }
            }
        }
        return load;
    };

    public static Character[][] tiltNorth(Character[][] puzzleGrid) {
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;
        boolean tilted = true;
        while (tilted) {
            tilted = false;
            for (int y = 1; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (puzzleGrid[y][x].equals('O') && puzzleGrid[y-1][x].equals('.')) {
                        puzzleGrid[y][x] = '.';
                        puzzleGrid[y-1][x] = 'O';
                        tilted = true;
                    }
                }
            }
        }
        return puzzleGrid;
    }

    public static Character[][] tiltSouth(Character[][] puzzleGrid) {
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;
        boolean tilted = true;
        while (tilted) {
            tilted = false;
            for (int y = height - 2; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    if (puzzleGrid[y][x].equals('O') && puzzleGrid[y+1][x].equals('.')) {
                        puzzleGrid[y][x] = '.';
                        puzzleGrid[y+1][x] = 'O';
                        tilted = true;
                    }
                }
            }
        }
        return puzzleGrid;
    }

    public static Character[][] tiltEast(Character[][] puzzleGrid) {
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;
        boolean tilted = true;
        while (tilted) {
            tilted = false;
            for (int x = width - 2; x >= 0; x--) {
                for (int y = 0; y < height; y++) {
                    if (puzzleGrid[y][x].equals('O') && puzzleGrid[y][x+1].equals('.')) {
                        puzzleGrid[y][x] = '.';
                        puzzleGrid[y][x+1] = 'O';
                        tilted = true;
                    }
                }
            }
        }
        return puzzleGrid;
    }

    public static Character[][] tiltWest(Character[][] puzzleGrid) {
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;
        boolean tilted = true;
        while (tilted) {
            tilted = false;
            for (int x = 1; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (puzzleGrid[y][x].equals('O') && puzzleGrid[y][x-1].equals('.')) {
                        puzzleGrid[y][x] = '.';
                        puzzleGrid[y][x-1] = 'O';
                        tilted = true;
                    }
                }
            }
        }
        return puzzleGrid;
    }

    public static String computeString(Character[][] puzzleGrid) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character[] line : puzzleGrid) {
            stringBuilder.append(Arrays.toString(line));
        }
        return stringBuilder.toString();
    }
}
