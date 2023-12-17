import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dec17 {

    public static void main(String[] args) {
        Integer[][] puzzleGrid = InputLoader.loadPuzzleGrid();

        Set<GridState> seen = new HashSet<>();
        PriorityQueue<GridState> heap = new PriorityQueue<>();
        heap.add(new GridState(0, 0, 0, Direction.STILL, 0));

        final int height = puzzleGrid.length;
        final int width = puzzleGrid[0].length;

        int heatLoss = 0;

        while (!heap.isEmpty()) {
            GridState currentState = heap.poll();
            int row = currentState.row;
            int col = currentState.col;
            int currentHeatLoss = currentState.heatLoss;
            Direction currentDirection = currentState.direction;
            int consecDirection = currentState.consecDirection;

            // Found the destination. Only valid if we've been going in the same direction at least 4 times and less than 10 times
            if (row == (height - 1) && col == (width - 1) && consecDirection >= 4) {
                heatLoss = currentHeatLoss;
                break;
            }

            // If we've been in this exact state before, skip
            if (seen.contains(currentState)) {
                continue;
            }

            // Add the current state to the set of states that we've encountered
            seen.add(currentState);

            // If there's still room to travel in the current direction. Add it to the heap and try to traverse further
            if (consecDirection < 10 && !currentDirection.equals(Direction.STILL)) {
                int newRow = row + currentDirection.row;
                int newCol = col + currentDirection.col;
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                    heap.add(new GridState(currentHeatLoss + puzzleGrid[newRow][newCol],
                            newRow,
                            newCol,
                            currentDirection,
                            consecDirection + 1));
                }
            }

            // Try turning. Can only turn if we have been doing in the same direction at least 4 times
            if (consecDirection >= 4 || currentDirection.equals(Direction.STILL)) {
                for (Direction direction : Direction.moving()) {
                    // Don't move in the current direction, and don't move in the opposite of the current direction
                    if (!direction.equals(currentDirection) && !direction.opposite().equals(currentDirection)) {
                        int newRow = row + direction.row;
                        int newCol = col + direction.col;
                        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                            heap.add(new GridState(currentHeatLoss + puzzleGrid[newRow][newCol],
                                    newRow,
                                    newCol,
                                    direction,
                                    1));
                        }
                    }
                }
            }
        }

        System.out.println("Shortest path: " + heatLoss);
    }
}
