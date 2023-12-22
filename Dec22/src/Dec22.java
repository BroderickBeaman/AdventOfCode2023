import java.util.*;

public class Dec22 extends AOCParent {
    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        List<Brick> bricks = new ArrayList<>(InputLoader.loadBricks());
        startPart(1);

        // Sort bricks by minimum z value.
        Collections.sort(bricks);

        // Ignore return value, we're just performing an initial fall
        countFallingBricks(bricks);

        // Sort the bricks after they've fallen. Order might have changed
        Collections.sort(bricks);

        Map<Integer, Set<Integer>> supportedBy = new HashMap<>();
        for (int i = 0; i < bricks.size(); i++) {
            supportedBy.put(i, new HashSet<>());
        }

        for (int i = bricks.size() - 1; i > 0; i--) {
            Brick currentBrick = bricks.get(i);
            for (int j = i - 1; j >= 0; j--) {
                Brick other = bricks.get(j);
                // If other directly supports z
                if (currentBrick.overlaps(other) && currentBrick.minZ() == (other.maxZ() + 1)) {
                    supportedBy.get(i).add(j);
                }
            }
        }

        int numSafeBricks = 0;
        for (int i = 0; i < bricks.size(); i++) {
            final int index = i;
            boolean safe = supportedBy.values().stream()
                    .filter(set -> set.contains(index)) // Find all bricks supported by the current brick
                    .noneMatch(set -> set.size() == 1); // If any bricks are only supported by i, it's unsafe
            if (safe) {
                numSafeBricks++;
            }
        }

        System.out.println("Number of safe bricks: " + numSafeBricks);

        endPart();
    }

    public static void part2() {
        List<Brick> bricks = new ArrayList<>(InputLoader.loadBricks());
        startPart(2);

        // Sort bricks by minimum z value.
        Collections.sort(bricks);

        // Initial brick fall. Ignore return value, we're just doing an initial fall;
        countFallingBricks(bricks);

        // Sort the bricks after they've fallen. Order might have changed
        Collections.sort(bricks);

        int numberOfFallingBricks = 0;
        for (int i = 0; i < bricks.size(); i++) {
            List<Brick> bricksWithOneRemoved = new ArrayList<>();
            for (int j = 0; j < bricks.size(); j++) {
                if (i != j) {
                    bricksWithOneRemoved.add(bricks.get(j).copy());
                }
            }
            assert bricksWithOneRemoved.size() == bricks.size() - 1;
            numberOfFallingBricks += countFallingBricks(bricksWithOneRemoved);
        }


        System.out.println("Number of bricks that would fall: " + numberOfFallingBricks);

        endPart();
    }

    public static int countFallingBricks(List<Brick> bricks) {
        final int lowestHeight = 1;

        int numBricksFalling = 0;

        for (int i = 0; i < bricks.size(); i++) {
            Brick currentBrick = bricks.get(i);
            int maxBelow = lowestHeight;
            for (int j = 0; j < i; j++) {
                Brick compare = bricks.get(j);
                if (currentBrick.overlaps(compare)) {
                    maxBelow = Math.max(maxBelow, compare.maxZ() + 1);
                }
            }

            if (currentBrick.minZ() > maxBelow) {
                numBricksFalling++;
                currentBrick.subtractZ(currentBrick.minZ() - maxBelow);
            }
        }

        return numBricksFalling;
    }
}
