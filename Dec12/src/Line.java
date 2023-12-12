import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {

    private final List<Spring> springs;
    private final List<Integer> groups;

    public Line(List<Spring> springs, List<Integer> groups) {
        this.springs = springs;
        this.groups = groups;
    }

    public long countPossibilities() {
        Map<String, Long> preComputed = new HashMap<>();
        return countPossibilities(springs, groups, preComputed);
    }

    private long countPossibilities(List<Spring> springs, List<Integer> groups, Map<String, Long> preComputed) {

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

        String cacheKey = buildCacheKey(springs, groups);

        if (preComputed.containsKey(cacheKey)) {
            return preComputed.get(cacheKey);
        }

        long numPossibilities = 0;

        // If working or unknown, assume it's working. Recurse on the rest of springs
        if (springs.get(0).equals(Spring.WORKING) || springs.get(0).equals(Spring.UNKNOWN)) {
            numPossibilities += countPossibilities(springs.subList(1, springs.size()),
                    groups,
                    preComputed);
        }

        // If broken or unknown, assume it's broken.
        if (springs.get(0).equals(Spring.BROKEN) || springs.get(0).equals(Spring.UNKNOWN)) {
            Integer groupSize = groups.get(0);
            if (
                    // Checks that there are enough springs
                    groupSize <= springs.size()
                    // Checks that all springs in range could be broken
                    && !springs.subList(0, groupSize).contains(Spring.WORKING)
                    // Checks that spring after range could be working (and would terminate group)
                    && (groupSize == springs.size() || !springs.get(groupSize).equals(Spring.BROKEN))) {
                numPossibilities += countPossibilities(springs.subList(Math.min(groupSize + 1, springs.size()), springs.size()),
                        groups.subList(1, groups.size()),
                        preComputed);
            }
        }

        preComputed.put(cacheKey, numPossibilities);
        return numPossibilities;
    }

    public String buildCacheKey(List<Spring> springs, List<Integer> groups) {
        return String.join("", springs.stream().map(Spring::toString).toList()) +
                "|" +
                String.join(",", groups.stream().map(Object::toString).toList());
    }
}
