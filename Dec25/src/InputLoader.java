import java.util.*;

public class InputLoader extends InputLoaderParent {

    public static List<Set<String>> loadWires() {
        List<String> allLines = loadLines();

        Set<Set<String>> wireSet = new HashSet<>();

        for (String line : allLines) {
            line = line.replace(":", "");
            String[] parts = line.split(" ");
            for (int i = 1; i < parts.length; i++) {
                wireSet.add(Set.of(parts[0], parts[i]));
            }
        }

        return new ArrayList<>(wireSet);
    }

    public static Map<String, Set<String>> loadWireMap() {
        List<String> allLines = loadLines();

        Map<String, Set<String>> wireMap = new HashMap<>();

        Set<String> nodes = new HashSet<>();

        for (String line : allLines) {
            line = line.replace(":", "");
            String[] parts = line.split(" ");
            nodes.addAll(Arrays.asList(parts));
        }

        for (String node : nodes) {
            wireMap.put(node, new HashSet<>());
        }

        for (String line : allLines) {
            line = line.replace(":", "");
            String[] parts = line.split(" ");
            String node = parts[0];
            for (int i = 1; i < parts.length; i++) {
                String connection = parts[i];
                wireMap.get(node).add(connection);
                wireMap.get(connection).add(node);
            }
        }

        return wireMap;
    }

    public static List<String> loadNodes() {
        List<String> allLines = loadLines();
        Set<String> nodes = new HashSet<>();

        for (String line : allLines) {
            line = line.replace(":", "");
            String[] parts = line.split(" ");
            nodes.addAll(Arrays.asList(parts));
        }

        return new ArrayList<>(nodes);
    }
}
