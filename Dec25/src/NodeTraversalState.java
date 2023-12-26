import java.util.Set;

public record NodeTraversalState(String currentNode, Set<Set<String>> seenWires) {

}
