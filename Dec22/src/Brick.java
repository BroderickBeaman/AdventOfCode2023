import java.util.Objects;

public class Brick implements Comparable<Brick> {

    private Point end1;
    private Point end2;

    public Brick(Point end1, Point end2) {
        this.end1 = end1;
        this.end2 = end2;
    }

    public void subtractZ(Integer z) {
        end1 = end1.subtractZ(z);
        end2 = end2.subtractZ(z);
    }

    public Integer minX() {
        return Math.min(end1.x(), end2.x());
    }

    public Integer minY() {
        return Math.min(end1.y(), end2.y());
    }

    public Integer maxX() {
        return Math.max(end1.x(), end2.x());
    }

    public Integer maxY() {
        return Math.max(end1.y(), end2.y());
    }

    public Integer minZ() {
        return Math.min(end1.z(), end2.z());
    }

    public Integer maxZ() {
        return Math.max(end1.z(), end2.z());
    }

    public boolean xOverlap(Brick other) {
        return this.minX() <= other.maxX() && this.maxX() >= other.minX();
    }

    public boolean yOverlap(Brick other) {
        return this.minY() <= other.maxY() && this.maxY() >= other.minY();
    }

    public boolean overlaps(Brick other) {
        return xOverlap(other) && yOverlap(other);
    }

    public Brick copy() {
        return new Brick(end1, end2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brick brick = (Brick) o;
        return Objects.equals(end1, brick.end1) && Objects.equals(end2, brick.end2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(end1, end2);
    }

    @Override
    public int compareTo(Brick other) {
        return minZ().compareTo(other.minZ());
    }
}
