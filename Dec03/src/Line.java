import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line {

    private static final Pattern integerPattern = Pattern.compile("\\d+");
    private static final Pattern symbolPattern = Pattern.compile("[^\\d.]");
    private static final Pattern gearPattern = Pattern.compile("\\*");

    private String rawInput;

    private List<NumberPosition> numberPositions;

    private List<Integer> symbolPositions;

    private List<Integer> potentialGearPositions;

    public Line(String rawInput) {
        this.rawInput = rawInput;
        Matcher intMatcher = integerPattern.matcher(rawInput);
        numberPositions = new ArrayList<>();
        while (intMatcher.find()) {
            numberPositions.add(new NumberPosition(intMatcher.group(), intMatcher.start(), rawInput.length()));
        }

        symbolPositions = new ArrayList<>();
        Matcher symbolMatcher = symbolPattern.matcher(rawInput);
        while (symbolMatcher.find()) {
            symbolPositions.add(symbolMatcher.start());
        }

        potentialGearPositions = new ArrayList<>();
        Matcher gearMatcher = gearPattern.matcher(rawInput);
        while (gearMatcher.find()) {
            potentialGearPositions.add(gearMatcher.start());
        }
    }

    public List<NumberPosition> getNumberPositions() {
        return numberPositions;
    }

    public int addNumsThatAreAdjacent(Line previous, Line next) {
        return numberPositions.stream().mapToInt(numberPosition -> {
            boolean isAdjacent = checkLine(numberPosition, previous)
                    || checkLine(numberPosition, this)
                    || checkLine(numberPosition, next);

            return isAdjacent ? Integer.parseInt(numberPosition.getNumberString()) : 0;
        }).sum();
    }

    public boolean checkLine(NumberPosition numberPosition, Line line) {
        if (line == null) {
            return false;
        }

        boolean isAdjacent = false;
        for (Integer symbolPosition : line.symbolPositions) {
            isAdjacent = isAdjacent || numberPosition.isAdjacent(symbolPosition);
        }
        return isAdjacent;
    }

    public List<Gear> findGears(Line previous, Line next) {
        return potentialGearPositions.stream().map(position -> {
            Gear gear = new Gear();
            if (previous != null) {
                addAdjacentIntsToGear(gear, position, previous);
            }

            addAdjacentIntsToGear(gear, position, this);

            if (next != null) {
                addAdjacentIntsToGear(gear, position, next);
            }

            return gear;
        }).filter(Gear::isGear).toList();
    }

    public void addAdjacentIntsToGear(Gear gear, int index, Line line) {
        for (NumberPosition numberPosition : line.getNumberPositions()) {
            if (index >= numberPosition.getAdjacentMin() && index <= numberPosition.getAdjacentMax()) {
                gear.addInteger(Integer.parseInt(numberPosition.getNumberString()));
            }
        }
    }
}
