import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Dec20 {

    public static void main(String[] args) {
        part1();
        System.out.println();
        part2();
    }

    private static void part2() {
        System.out.println("=== Part 2 ===");
        Map<String, Module> moduleMap = InputLoader.loadModules();
        long start = System.currentTimeMillis();
        Queue<PulseCommand> commandQueue = new LinkedList<>();
        ConjunctionModule hp = (ConjunctionModule) moduleMap.get("hp");
        Map<String, Long> seenHpOriginHigh = new HashMap<>();
        for (String inputModule : hp.getInputModuleMemory().keySet()) {
            seenHpOriginHigh.put(inputModule, 0L);
        }
        Map<String, Long> cycleLengths = new HashMap<>();
        long presses = 1;
        boolean keepGoing = true;
        while(keepGoing) {
            commandQueue.add(new PulseCommand(Pulse.LOW, "broadcaster", "button"));
            while (!commandQueue.isEmpty()) {
                PulseCommand command = commandQueue.poll();
                if (command.destination().equals("hp") && command.pulse().equals(Pulse.HIGH)) {
                    String source = command.source();
                    seenHpOriginHigh.put(source, seenHpOriginHigh.get(source) + 1);
                    if (!cycleLengths.containsKey(source)) {
                        cycleLengths.put(source, presses);
                    }

                    // We've seen a high value on each input to hp.
                    if (!seenHpOriginHigh.containsValue(0L)) {
                        keepGoing = false;
                        break;
                    }
                }

                if (moduleMap.containsKey(command.destination())) {
                    Module destinationModule = moduleMap.get(command.destination());
                    destinationModule.receivePulse(command.pulse(), command.source(), commandQueue);
                }
            }

            presses++;
        }

        long numPresses = cycleLengths.values().stream().reduce(1L, (a,b) -> a*b);
        long end = System.currentTimeMillis();
        System.out.println("Minimum number of presses: " + numPresses);
        System.out.println("Total execution time: " + (end - start) + "ms");
    }

    private static void part1() {
        System.out.println("=== Part 1 ===");
        Map<String, Module> moduleMap = InputLoader.loadModules();
        long start = System.currentTimeMillis();
        long totalHigh = 0;
        long totalLow = 0;
        Queue<PulseCommand> commandQueue = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            commandQueue.add(new PulseCommand(Pulse.LOW, "broadcaster", "button"));
            while (!commandQueue.isEmpty()) {
                PulseCommand command = commandQueue.poll();
                if (command.pulse().equals(Pulse.HIGH)) {
                    totalHigh++;
                } else {
                    totalLow++;
                }

                if (moduleMap.containsKey(command.destination())) {
                    Module destinationModule = moduleMap.get(command.destination());
                    destinationModule.receivePulse(command.pulse(), command.source(), commandQueue);
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Total number of high presses: " + totalHigh);
        System.out.println("Total number of low presses: " + totalLow);
        System.out.println("Answer: " + (totalHigh * totalLow));
        System.out.println("Total execution time: " + (end - start) + "ms");
    }
}
