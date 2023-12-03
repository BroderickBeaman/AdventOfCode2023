import java.util.ArrayList;
import java.util.List;

public class Gear {

    private List<Integer> adjacentInts;

    public Gear() {
        adjacentInts = new ArrayList<>();
    }

    public void addInteger(Integer integer) {
        adjacentInts.add(integer);
    }

    public boolean isGear() {
        return adjacentInts.size() == 2;
    }

    public Integer getGearRatio() {
        return adjacentInts.stream().mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b);
    }
}
