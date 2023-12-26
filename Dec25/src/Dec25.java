import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Dec25 extends AOCParent {

    private static int numNodes;

    private static Map<String, Set<String>> wireMap;

    private static Set<String> seen;

    private static Set<Set<String>> bannedWires;
    private static List<String> nodes;
    private static int group1Size;
    private static int group2Size;

    private static Queue<NodeTraversalState> queue;

    private static NodeTraversalState stateFromAtoB;

    public static void main(String[] args) {
        part1();
    }

    private static void part1() {
        List<Set<String>> wireList = InputLoader.loadWires();
        nodes = InputLoader.loadNodes();
        numNodes = nodes.size();
        wireMap = InputLoader.loadWireMap();
        startPart(1);

        Map<Set<String>, Integer> wireCount = new HashMap<>();

        for (Set<String> wire : wireList) {
            wireCount.put(wire, 0);
        }

        for (int i = 0; i < 200; i++) {
            int node1Index = ThreadLocalRandom.current().nextInt(0, nodes.size());
            int node2Index = ThreadLocalRandom.current().nextInt(0, nodes.size());
            if (node1Index == node2Index) {
                continue;
            }

            String node1 = nodes.get(node1Index);
            String node2 = nodes.get(node2Index);
            seen = new HashSet<>();
            queue = new LinkedList<>();
            seen.add(node1);
            queue.add(new NodeTraversalState(node1, new HashSet<>()));

            while (!queue.isEmpty()) {
                if (nodeToNode(node2, queue.poll())) {
                    break;
                }
            }

            for (Set<String> wire : stateFromAtoB.seenWires()) {
                wireCount.put(wire, wireCount.get(wire) + 1);
            }
        }

        PriorityQueue<WireCount> maxHeap = new PriorityQueue<>();

        for (Map.Entry<Set<String>, Integer> entry : wireCount.entrySet()) {
            maxHeap.add(new WireCount(entry.getKey(), entry.getValue()));
        }

        List<Set<String>> curatedWireList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            curatedWireList.add(maxHeap.poll().wire());
        }

        boolean stop = false;

        for (int i = 0; i < curatedWireList.size(); i++) {
            for (int j = i + 1; j < curatedWireList.size(); j++) {
                for (int k = j + 1; k < curatedWireList.size(); k++) {
                    bannedWires = Set.of(curatedWireList.get(i), curatedWireList.get(j), curatedWireList.get(k));
                    seen = new HashSet<>();

                    // Pick random node and start traversing
                    traverseGraph("cxq");

                    group1Size = seen.size();

                    if (group1Size != numNodes) {
                        // Pick a node not seen and try traversing.

                        Set<String> otherNodes = new HashSet<>(wireMap.keySet());
                        otherNodes.removeAll(seen);
                        assert !otherNodes.isEmpty();

                        seen = new HashSet<>();
                        String newNode = otherNodes.stream().findAny().get();
                        traverseGraph(newNode);
                        group2Size = seen.size();

                        // We have exactly two groups
                        if (group1Size + group2Size == numNodes) {
                            stop = true;
                            break;
                        }
                    }

                }
                if (stop) {
                    break;
                }
            }
            if (stop) {
                break;
            }
        }

        if (!stop) {
            System.out.println("Didn't find solution");
        } else {
            System.out.println("Group 1 size: " + group1Size);
            System.out.println("Group 2 size: " + group2Size);
            System.out.println("Answer: " + (group1Size * group2Size));
        }
        endPart();
    }

    private static void traverseGraph(String node) {
        // We've seen all seenNodes. This graph isn't in two parts. No need to keep traversing
        if (seen.size() == numNodes) {
            return;
        }

        if (seen.contains(node)) {
            return;
        }

        seen.add(node);

        Set<String> connections = wireMap.get(node);

        if (connections == null || connections.isEmpty()) {
            return;
        }

        for (String connection : wireMap.get(node)) {
            if (!bannedWires.contains(Set.of(node, connection))) {
                traverseGraph(connection);
            }
        }
    }

    public static boolean nodeToNode(String dest, NodeTraversalState currentState) {
        if (currentState.currentNode().equals(dest)) {
            stateFromAtoB = currentState;
            return true;
        }

        seen.add(currentState.currentNode());

        for (String connection : wireMap.get(currentState.currentNode())) {
            if (seen.contains(connection)) {
                continue;
            }
            seen.add(connection);
            Set<Set<String>> wires = new HashSet<>(currentState.seenWires());
            wires.add(Set.of(currentState.currentNode(), connection));
            queue.add(new NodeTraversalState(connection, wires));
        }
        return false;
    }

}
