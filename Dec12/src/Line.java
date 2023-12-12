import java.util.ArrayList;
import java.util.List;

public class Line {

    private List<Spring> springs;
    private List<Integer> groups;

    public Line(List<Spring> springs, List<Integer> groups) {
        this.springs = springs;
        this.groups = groups;
    }

    public int countPossibilities() {
        return countPossibilities(springs, groups);
    }

    private int countPossibilities(List<Spring> springs, List<Integer> groups) {

        // Base case
        if (springs.isEmpty()) {
            // If there are no groups left, this is a valid configuration
            return groups.isEmpty() ? 1 : 0;
        }

        // Base case. No more groups to process
        if (groups.isEmpty()) {
            // If there are still broken springs, this configuration is invalid
            return springs.contains(Spring.BROKEN) ? 0 : 1;
        }

        int numPossibilities = 0;

        // If working or unknown, assume it's working. Recurse on the rest of springs
        if (springs.get(0).equals(Spring.WORKING) || springs.get(0).equals(Spring.UNKNOWN)) {
            numPossibilities += countPossibilities(springs.subList(1, springs.size()), groups);
        }

        // If broken or unknown, assume it's broken.
        if (springs.get(0).equals(Spring.BROKEN) || springs.get(0).equals(Spring.UNKNOWN)) {
            Integer groupSize = groups.get(0);
            boolean enoughSprings = groupSize <= springs.size();
            boolean allCouldBeBroken = !springs.subList(0, groupSize).contains(Spring.WORKING);
            boolean nextSpringWorking = groupSize == springs.size() || !springs.get(groupSize).equals(Spring.BROKEN);

            if (enoughSprings && allCouldBeBroken && nextSpringWorking) {
                numPossibilities += countPossibilities(springs.subList(groupSize + 1, springs.size()), groups.subList(1, groups.size()));
            }
        }

        return numPossibilities;
    }
}
