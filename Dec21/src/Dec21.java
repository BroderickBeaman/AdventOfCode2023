import java.util.HashSet;
import java.util.Set;

public class Dec21 {

    private static Space[][] puzzleGrid;
    private static int height;
    private static int width;
    private static Set<LocationState> recordedStates;
    private static Set<Location> validEndLocations;

    public static void main(String[] args) {
        puzzleGrid = InputLoader.loadPuzzleGrid();
        height = puzzleGrid.length;
        width = puzzleGrid[0].length;

        part1();
        System.out.println();
        part2();
    }

    private static void part1() {
        System.out.println("=== Part 1 ===");
        long start = System.currentTimeMillis();

        Location startLocation = null;
        boolean found = false;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (puzzleGrid[row][col].equals(Space.START)) {
                    found = true;
                    startLocation = new Location(row, col);
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        recordedStates = new HashSet<>();
        validEndLocations = new HashSet<>();

        traverseGrid(new LocationState(startLocation, 64L));

        long end = System.currentTimeMillis();

        System.out.println("Total number of possible end locations: " + validEndLocations.size());
        System.out.println("Total execution time: " + (end - start) + "ms");
    }

    private static void part2() {
        System.out.println("=== Part 2 ===");
        long start = System.currentTimeMillis();

        // Grid is a square
        final long size = height;
        final long totalSteps = 26501365L;

        Location startLocation = null;
        boolean found = false;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (puzzleGrid[row][col].equals(Space.START)) {
                    found = true;
                    startLocation = new Location(row, col);
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        assert startLocation != null;

        // Number of grids you can fully explore in a single direction
        long numSingleDirectionGrids = (totalSteps / size) - 1;

        // Every other grid should have an odd number of steps remaining, and every other one should have an even number
        // Count how many even grids there are and how many odd grids there are.
        // Every odd grid should have an identical solution, and every even grid should as well.
        // Except of course for the cases at the edge of the puzzle where we can't cover the total grid with our number
        // of remaining steps
        long numOddGrids = (long) Math.pow(((numSingleDirectionGrids / 2) * 2) + 1, 2);
        long numEvenGrids = (long) Math.pow((numSingleDirectionGrids + 1) / 2 * 2, 2);

        // Enough steps to hit every points on grid
        long endPointsOnOddGrid = countPoints(new LocationState(startLocation, size * 2 + 1));
        long endPointsOnEvenGrid = countPoints(new LocationState(startLocation, size * 2));

        long endPointsTopEdge = countPoints(new LocationState(new Location((int) (size - 1), startLocation.col()), size - 1));
        long endPointsRightEdge = countPoints(new LocationState(new Location(startLocation.row(), 0), size - 1));
        long endPointsBottomEdge = countPoints(new LocationState(new Location(0, startLocation.col()), size - 1));
        long endPointsLeftEdge = countPoints(new LocationState(new Location(startLocation.row(), (int) (size - 1)), size - 1));

        final long stepsForSmallCorners = (size / 2) - 1;
        long topRightSmallCorner = countPoints(new LocationState(new Location((int) (size - 1), 0), stepsForSmallCorners));
        long bottomRightSmallCorner = countPoints(new LocationState(new Location(0, 0), stepsForSmallCorners));
        long topLeftSmallCorner = countPoints(new LocationState(new Location((int) (size - 1), (int) (size - 1)), stepsForSmallCorners));
        long bottomLeftSmallCorner = countPoints(new LocationState(new Location(0, (int) (size - 1)), stepsForSmallCorners));

        final long stepsForBigSections = (size * 3 / 2) - 1;
        long topRightBigSection = countPoints(new LocationState(new Location((int) (size - 1), 0), stepsForBigSections));
        long bottomRightBigSection = countPoints(new LocationState(new Location(0, 0), stepsForBigSections));
        long topLeftBigSection = countPoints(new LocationState(new Location((int) (size - 1), (int) (size - 1)), stepsForBigSections));
        long bottomLeftBigSection = countPoints(new LocationState(new Location(0, (int) (size - 1)), stepsForBigSections));

        long numSmallTriangles = numSingleDirectionGrids + 1;

        long totalValidEndLocations = (numOddGrids * endPointsOnOddGrid) +
                (numEvenGrids * endPointsOnEvenGrid) +
                endPointsTopEdge +
                endPointsRightEdge +
                endPointsBottomEdge +
                endPointsLeftEdge +
                (topRightSmallCorner * numSmallTriangles) +
                (bottomRightSmallCorner * numSmallTriangles)+
                (topLeftSmallCorner * numSmallTriangles) +
                (bottomLeftSmallCorner * numSmallTriangles) +
                (topRightBigSection * numSingleDirectionGrids) +
                (bottomRightBigSection * numSingleDirectionGrids) +
                (topLeftBigSection * numSingleDirectionGrids) +
                (bottomLeftBigSection * numSingleDirectionGrids);

        long end = System.currentTimeMillis();
        System.out.println("Total number of possible end locations: " + totalValidEndLocations);
        System.out.println("Total execution time: " + (end - start) + "ms");
    }

    private static void reset() {
        recordedStates = new HashSet<>();
        validEndLocations = new HashSet<>();
    }

    private static long countPoints(LocationState locationState) {
        reset();
        traverseGrid(locationState);
        return validEndLocations.size();
    }

    private static void traverseGrid(LocationState currentState) {
        Location location = currentState.location();

        // Not a valid location. Out of bounds
        if (location.row() < 0 || location.row() >= height || location.col() < 0 || location.col() >= width) {
            return;
        }

        Space spaceAtLocation = puzzleGrid[location.row()][location.col()];

        // Not a valid location. Can't walk into wall
        if (spaceAtLocation.equals(Space.WALL)) {
            return;
        }

        // Add current location to set of end locations
        if (currentState.numSteps().equals(0L)) {
            validEndLocations.add(location);
            return;
        }

        // If we've already been here with the same amount of recorded steps, return
        if (recordedStates.contains(currentState)) {
            return;
        }

        for (Direction direction : Direction.values()) {
            traverseGrid(new LocationState(
                    new Location(location.row() + direction.row, location.col() + direction.col),
                    currentState.numSteps() - 1));
        }

        recordedStates.add(currentState);
    }
}
