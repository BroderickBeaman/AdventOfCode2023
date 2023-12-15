import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dec15 {
    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        List<Command> puzzleInputs = InputLoader.loadInputs().stream().map(Command::new).toList();

        Map<Long, List<Lens>> lensBoxes = new HashMap<>();
        for (long i = 0; i < 256; i++) {
            lensBoxes.put(i, new ArrayList<>());
        }

        for (Command command : puzzleInputs) {
            long boxNumber = command.getLens().hashLabel();
            if (command.getOperation().equals(Operation.ADD)) {
                if (lensBoxes.get(boxNumber).contains(command.getLens())) {
                    int indexOf = lensBoxes.get(boxNumber).indexOf(command.getLens());
                    lensBoxes.get(boxNumber).get(indexOf).setFocalLength(command.getLens().getFocalLength());
                } else {
                    lensBoxes.get(boxNumber).add(command.getLens());
                }
            } else {
                lensBoxes.get(boxNumber).remove(command.getLens());
            }
        }

        long total = 0;

        for (long i = 0; i < 256; i++) {
            List<Lens> lensBox = lensBoxes.get(i);
            if (lensBox.isEmpty()) {
                continue;
            }

            for (int j = 0; j < lensBox.size(); j++) {
                long lensTotal = i + 1;
                lensTotal = lensTotal * (j + 1);
                lensTotal = lensTotal * lensBox.get(j).getFocalLength();
                total += lensTotal;
            }
        }

        System.out.println("Sum of lens power: " + total);
    }

    private static void part1() {
        List<String> puzzleInputs = InputLoader.loadInputs();
        long result = puzzleInputs.stream().mapToLong(Dec15::hashInput).sum();

        System.out.println("Sum of hashes: " + result);
    }

    public static long hashInput(String string) {
        long hash = 0;
        for (int i = 0; i < string.length(); i++) {
            hash += string.charAt(i);
            hash = hash * 17;
            hash = hash % 256;
        }
        return hash;
    }
}
