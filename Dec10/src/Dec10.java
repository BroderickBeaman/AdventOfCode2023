import java.util.ArrayList;
import java.util.List;

public class Dec10 {
    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        Pipe[][] puzzleGrid = InputLoader.loadInput();

        int x, y;
        int startX = 0, startY = 0;

        int height = puzzleGrid.length, width = puzzleGrid[0].length;

        // Find start
        boolean foundStart = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Pipe.START.equals(puzzleGrid[i][j])) {
                    foundStart = true;
                    startY = i;
                    startX = j;
                    break;
                }
            }
            if (foundStart) {
                break;
            }
        }

        List<Point> pointsInPolygon = new ArrayList<>();
        List<Point> pointsOnLine = new ArrayList<>();
        pointsInPolygon.add(new Point(startX, startY));
        pointsOnLine.add(new Point(startX, startY));

        // Manually insert first pipe to check
        x = startX + 0;
        y = startY - 1;

        Direction from = Direction.SOUTH;

        // Compute points
        while (x != startX || y != startY) {
            Pipe pipe = puzzleGrid[y][x];
            switch (pipe) {
                case NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST -> pointsInPolygon.add(new Point(x, y));
            }
            pointsOnLine.add(new Point(x, y));

            Direction next = pipe.nextDirection(from);

            switch (next) {
                case NORTH -> y--;
                case SOUTH -> y++;
                case EAST -> x++;
                case WEST -> x--;
            }

            from = next.opposite();
        }

        Polygon polygon = new Polygon(pointsInPolygon);

        int containedPoints = 0;

        for (int puzzleY = 0; puzzleY < height; puzzleY++) {
            for (int puzzleX = 0; puzzleX < width; puzzleX++) {
                Point point = new Point(puzzleX, puzzleY);
                if (!pointsOnLine.contains(point)) {
                    if (polygon.contains(point)) {
                        containedPoints++;
                    }
                }

            }
        }

        System.out.println("Number of points in polygon: " + containedPoints);
    }

    private static void part1() {
        Pipe[][] puzzleGrid = InputLoader.loadInput();

        int x1, x2, y1, y2;

        int startX = 0, startY = 0;

        int height = puzzleGrid.length, width = puzzleGrid[0].length;

        boolean foundStart = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (Pipe.START.equals(puzzleGrid[i][j])) {
                    foundStart = true;
                    startY = i;
                    startX = j;
                    break;
                }
            }
            if (foundStart) {
                break;
            }
        }

        // Skipping calculating pipe start and end. Just insert coordinates here
        y1 = startY - 1;
        x1 = startX + 0;
        y2 = startY + 0;
        x2 = startX - 1;

        Direction from1 = Direction.SOUTH;
        Direction from2 = Direction.EAST;

        int numberOfSteps = 0;

        for(;x1 != x2 || y1 != y2; numberOfSteps++) {
            Pipe pipe1 = puzzleGrid[y1][x1];
            Pipe pipe2 = puzzleGrid[y2][x2];

            Direction next1 = pipe1.nextDirection(from1);
            Direction next2 = pipe2.nextDirection(from2);

            switch (next1) {
                case NORTH -> y1--;
                case SOUTH -> y1++;
                case EAST -> x1++;
                case WEST -> x1--;
            }

            switch (next2) {
                case NORTH -> y2--;
                case SOUTH -> y2++;
                case EAST -> x2++;
                case WEST -> x2--;
            }

            from1 = next1.opposite();
            from2 = next2.opposite();
        }

        System.out.println("Distance from start: " + (numberOfSteps + 1));
    }

}
