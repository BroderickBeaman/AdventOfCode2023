import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dec23 extends AOCParent {

    private static Space[][] puzzleGrid;
    private static int height;
    private static int width;

    private static int maxLength = 0;

    private static Point startLocation;
    private static Point endLocation;
    private static Set<Point> visited;

    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        puzzleGrid = InputLoader.loadPuzzleGrid();
        startPart(1);

        height = puzzleGrid.length;
        width = puzzleGrid[0].length;
        maxLength = 0;
        int startCol = 0;
        int endCol = 0;
        for (int i = 0; i < width; i++) {
            if (puzzleGrid[0][i].equals(Space.EMPTY)) {
                startCol = i;
            }
            if (puzzleGrid[height - 1][i].equals(Space.EMPTY)) {
                endCol = i;
            }
        }

        startLocation = new Point(0, startCol);
        endLocation = new Point(height - 1, endCol);
        visited = new HashSet<>();

        traverseGridPart1(startLocation, Direction.SOUTH, 0);
        System.out.println("Longest walk: " + maxLength + " steps");
        endPart();
    }

    public static void part2() {
        startPart(2);

        height = puzzleGrid.length;
        width = puzzleGrid[0].length;
        maxLength = 0;
        int startCol = 0;
        int endCol = 0;
        for (int i = 0; i < width; i++) {
            if (puzzleGrid[0][i].equals(Space.EMPTY)) {
                startCol = i;
            }
            if (puzzleGrid[height - 1][i].equals(Space.EMPTY)) {
                endCol = i;
            }
        }

        startLocation = new Point(0, startCol);
        endLocation = new Point(height - 1, endCol);
        visited = new HashSet<>();

        traverseGridPart2(startLocation, Direction.SOUTH, 0);
        System.out.println("Longest walk: " + maxLength + " steps");

        endPart();
    }

    private static void traverseGridPart1(Point location, Direction direction, int numSteps) {
        if (location.row() < 0 || location.row() >= height || location.col() < 0 || location.col() >= width) {
            return;
        }

        Space space = puzzleGrid[location.row()][location.col()];

        if (space.equals(Space.WALL)) {
            return;
        }

        if (location.equals(endLocation)) {
            maxLength = Math.max(maxLength, numSteps);
            return;
        }

        if (visited.contains(location)) {
            return;
        }

        visited.add(location);

        List<Direction> nextDirections;

        if (space.isSlope()) {
            nextDirections = List.of(space.nextDirection());
        } else {
            nextDirections = direction.allMinusOpposite();
        }

        for (Direction nextDirection : nextDirections) {
            traverseGridPart1(new Point(location.row() + nextDirection.row, location.col() + nextDirection.col),
                    nextDirection, numSteps + 1);
        }

        visited.remove(location);
    }

    private static void traverseGridPart2(Point location, Direction direction, int numSteps) {
        if (location.row() < 0 || location.row() >= height || location.col() < 0 || location.col() >= width) {
            return;
        }

        Space space = puzzleGrid[location.row()][location.col()];

        if (space.equals(Space.WALL)) {
            return;
        }

        if (location.equals(endLocation)) {
            maxLength = Math.max(maxLength, numSteps);
            return;
        }

        if (visited.contains(location)) {
            return;
        }

        visited.add(location);

        List<Direction> nextDirections = direction.allMinusOpposite();


        for (Direction nextDirection : nextDirections) {
            traverseGridPart2(new Point(location.row() + nextDirection.row, location.col() + nextDirection.col),
                    nextDirection, numSteps + 1);
        }

        visited.remove(location);
    }
}
