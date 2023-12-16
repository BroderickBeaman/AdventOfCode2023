import java.util.ArrayList;
import java.util.List;

public class Dec16 {

    private static Space[][] puzzleGrid;
    private static List<Direction>[][] visitedSpaces;
    private static int width, height;

    public static void main(String[] args) {
        puzzleGrid = InputLoader.loadPuzzleGrid();

        height = puzzleGrid.length;
        width = puzzleGrid[0].length;

        long bestConfiguration = 0;

        //try from top
        for (int i = 0; i < width; i++) {
            visitedSpaces = new List[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    visitedSpaces[y][x] = new ArrayList<>();
                }
            }
            traversePuzzle(i, 0, Direction.DOWN);
            bestConfiguration = Math.max(numSpacesVisited(), bestConfiguration);
        }

        //try from bottom
        for (int i = 0; i < width; i++) {
            visitedSpaces = new List[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    visitedSpaces[y][x] = new ArrayList<>();
                }
            }
            traversePuzzle(i, height - 1, Direction.UP);
            bestConfiguration = Math.max(numSpacesVisited(), bestConfiguration);
        }

        //try from left
        for (int i = 0; i < width; i++) {
            visitedSpaces = new List[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    visitedSpaces[y][x] = new ArrayList<>();
                }
            }
            traversePuzzle(0, i, Direction.RIGHT);
            bestConfiguration = Math.max(numSpacesVisited(), bestConfiguration);
        }

        //try from right
        for (int i = 0; i < width; i++) {
            visitedSpaces = new List[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    visitedSpaces[y][x] = new ArrayList<>();
                }
            }
            traversePuzzle(width - 1, i, Direction.LEFT);
            bestConfiguration = Math.max(numSpacesVisited(), bestConfiguration);
        }

        System.out.println("Number of spaces energized: " + bestConfiguration);
    }

    private static void traversePuzzle(int x, int y, Direction currentDirection) {
        // Base case. Beam made it outside grid
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        // Found a loop. Kill the current beam
        if (visitedSpaces[y][x].contains(currentDirection)) {
            return;
        }

        visitedSpaces[y][x].add(currentDirection);
        List<Direction> nextDirections = puzzleGrid[y][x].nextDirection(currentDirection);
        for (Direction direction : nextDirections) {
            switch (direction) {
                case UP -> traversePuzzle(x, y - 1, direction);
                case DOWN -> traversePuzzle(x, y + 1, direction);
                case LEFT -> traversePuzzle(x - 1, y, direction);
                case RIGHT -> traversePuzzle(x + 1, y, direction);
            }
        }
    }

    private static long numSpacesVisited() {
        long sum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(!visitedSpaces[y][x].isEmpty()) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
