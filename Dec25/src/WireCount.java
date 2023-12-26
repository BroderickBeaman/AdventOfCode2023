import java.util.Set;

public record WireCount(Set<String> wire, Integer count) implements Comparable<WireCount> {
    @Override
    public int compareTo(WireCount o) {
        return o.count.compareTo(count);
    }
}
