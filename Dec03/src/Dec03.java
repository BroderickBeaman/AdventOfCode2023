import java.util.List;

public class Dec03 {

    public static void main(String[] args) {
        List<Line> lines = LineLoader.loadInput();

        int sum = part2(lines);
        System.out.println("Sum: " + sum);
    }

    private static int part2(List<Line> lines) {
        int sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            Line previous = null;
            Line next = null;

            try {
                previous = lines.get(i - 1);
            } catch (Exception e) {}

            try {
                next = lines.get(i + 1);
            } catch (Exception e) {}

            sum += lines.get(i).findGears(previous, next).stream().mapToInt(Gear::getGearRatio).sum();

        }
        return sum;
    }

    private static int part1(List<Line> lines) {
        int sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            Line previous = null;
            Line next = null;

            try {
                previous = lines.get(i - 1);
            } catch (Exception e) {}

            try {
                next = lines.get(i + 1);
            } catch (Exception e) {}

            sum += lines.get(i).addNumsThatAreAdjacent(previous, next);
        }
        return sum;
    }
}
