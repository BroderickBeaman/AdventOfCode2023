import java.util.ArrayList;
import java.util.List;

public class Dec11 {

    public static void main(String[] args) {
        InterstellarObject[][] puzzleGrid = InputLoader.loadInput();

        List<Point> galaxies = new ArrayList<>();
        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;

        // Populate galaxies
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (InterstellarObject.GALAXY.equals(puzzleGrid[y][x])) {
                    galaxies.add(new Point(x, y));
                }
            }
        }

        List<Integer> emptyRow = new ArrayList<>();
        List<Integer> emptyColumn = new ArrayList<>();

        // Populate empty rows
        for (int y = 0; y < height; y++) {
            boolean empty = true;
            for (int x = 0; x < width; x++) {
                if (InterstellarObject.GALAXY.equals(puzzleGrid[y][x])) {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                emptyRow.add(y);
            }
        }

        // Populate empty columns
        for (int x = 0; x < width; x++) {
            boolean empty = true;
            for (int y = 0; y < height; y++) {
                if (InterstellarObject.GALAXY.equals(puzzleGrid[y][x])) {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                emptyColumn.add(x);
            }
        }

        List<Point> galaxiesToCompare = new ArrayList<>(galaxies);
        long distanceSum = 0L;
        for (Point galaxy : galaxies) {
            distanceSum += galaxiesToCompare.stream()
                    .mapToLong(toCompare -> galaxy.distance(toCompare, emptyRow, emptyColumn)).sum();
            galaxiesToCompare.remove(galaxy);
        }

        System.out.println("Sum of distances: " + distanceSum);
    }
}
