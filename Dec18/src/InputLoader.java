import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Instruction> loadInstructionsPart1() {
        List<String> allLines = loadLines();

        return allLines.stream().map(line -> {
            String[] parts = line.split(" ");
            assert parts.length == 3;
            Direction direction = Direction.fromChar(parts[0].charAt(0));
            Long distance = Long.parseLong(parts[1]);
            return new Instruction(direction, distance);
        }).toList();
    }

    public static List<Instruction> loadInstructionsPart2() {
        List<String> allLines = loadLines();
        return allLines.stream().map(line -> {
            String[] parts = line.split("\\(#");
            assert parts.length == 2;
            long distance = Long.parseLong(parts[1].substring(0, 5), 16);
            Direction direction = Direction.fromHexChar(parts[1].charAt(5));
            return new Instruction(direction, distance);
        }).toList();
    }
}
