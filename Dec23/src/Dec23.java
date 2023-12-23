import java.util.*;

public class Dec23 extends AOCParent {

    private static Space[][] puzzleGrid;
    private static int height;
    private static int width;

    private static int maxLength = 0;

    private static Point startLocation;
    private static Point endLocation;
    private static Set<Point> visited;
    private static Map<Point, Set<Edge>> edgeMap;

    private static Set<Point> pointsOfInterest;

    public static void main(String[] args) {
        puzzleGrid = InputLoader.loadPuzzleGrid();
        part1();
        part2();
    }

    public static void part1() {
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

        pointsOfInterest = new HashSet<>();
        pointsOfInterest.add(startLocation);
        pointsOfInterest.add(endLocation);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // This is a junction
                if (!puzzleGrid[row][col].equals(Space.WALL) && getNeighbourCount(row, col) > 2) {
                    pointsOfInterest.add(new Point(row, col));
                }
            }
        }

        computeEdgesPart1();

        traverseGridPart(startLocation, 0);
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

        pointsOfInterest = new HashSet<>();
        pointsOfInterest.add(startLocation);
        pointsOfInterest.add(endLocation);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // This is a junction
                if (!puzzleGrid[row][col].equals(Space.WALL) && getNeighbourCount(row, col) > 2) {
                    pointsOfInterest.add(new Point(row, col));
                }
            }
        }

        computeEdgesPart2();

        traverseGridPart(startLocation, 0);
        System.out.println("Longest walk: " + maxLength + " steps");

        endPart();
    }

    private static void traverseGridPart(Point location, int numSteps) {
        if (location.equals(endLocation)) {
            maxLength = Math.max(maxLength, numSteps);
            return;
        }

        if (visited.contains(location)) {
            return;
        }

        visited.add(location);

        for (Edge edge : edgeMap.get(location)) {
            traverseGridPart(edge.toPoint(), numSteps + edge.length());
        }

        visited.remove(location);
    }

    public static int getNeighbourCount(int row, int col) {
        int count = 0;
        for (Direction direction : Direction.values()) {
            int newRow = row + direction.row;
            int newCol = col + direction.col;
            if (isInGrid(newRow, newCol) && !puzzleGrid[newRow][newCol].equals(Space.WALL)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isInGrid(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public static void computeEdgesPart1() {
        edgeMap = new HashMap<>();
        for (Point point : pointsOfInterest) {
            edgeMap.put(point, computeEdgesPart1(point, point, new HashSet<>(), 0, new HashSet<>()));
        }
    }

    public static Set<Edge> computeEdgesPart1(Point startLocation, Point location, Set<Point> seen, int numSteps, Set<Edge> edges) {
        if (location.row() < 0 || location.row() >= height || location.col() < 0 || location.col() >= width) {
            return edges;
        }

        Space space = puzzleGrid[location.row()][location.col()];

        if (space.equals(Space.WALL)) {
            return edges;
        }

        if (seen.contains(location)) {
            return edges;
        }

        if (!location.equals(startLocation) && pointsOfInterest.contains(location)) {
            edges.add(new Edge(location, numSteps));
            return edges;
        }

        seen.add(location);

        List<Direction> nextDirections;

        if (space.isSlope()) {
            nextDirections = List.of(space.nextDirection());
        } else {
            nextDirections = List.of(Direction.values());
        }

        for (Direction nextDirection : nextDirections) {
            Point nextPoint = new Point(location.row() + nextDirection.row, location.col() + nextDirection.col);
            edges = computeEdgesPart1(startLocation, nextPoint, seen, numSteps + 1, edges);
        }

        seen.remove(location);
        return edges;
    }

    public static void computeEdgesPart2() {
        edgeMap = new HashMap<>();
        for (Point point : pointsOfInterest) {
            edgeMap.put(point, computeEdgesPart2(point, point, new HashSet<>(), 0, new HashSet<>()));
        }
    }

    public static Set<Edge> computeEdgesPart2(Point startLocation, Point location, Set<Point> seen, int numSteps, Set<Edge> edges) {
        if (location.row() < 0 || location.row() >= height || location.col() < 0 || location.col() >= width) {
            return edges;
        }

        Space space = puzzleGrid[location.row()][location.col()];

        if (space.equals(Space.WALL)) {
            return edges;
        }

        if (seen.contains(location)) {
            return edges;
        }

        if (!location.equals(startLocation) && pointsOfInterest.contains(location)) {
            edges.add(new Edge(location, numSteps));
            return edges;
        }

        seen.add(location);

        List<Direction> nextDirections = List.of(Direction.values());

        for (Direction nextDirection : nextDirections) {
            Point nextPoint = new Point(location.row() + nextDirection.row, location.col() + nextDirection.col);
            edges = computeEdgesPart2(startLocation, nextPoint, seen, numSteps + 1, edges);
        }

        seen.remove(location);
        return edges;
    }
}
