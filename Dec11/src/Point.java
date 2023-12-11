import java.util.List;

public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Point)) {
            return false;
        }

        Point point = (Point) obj;

        return this.x == point.x && this.y == point.y;
    }

    public long distance(Point other, List<Integer> emptyRows, List<Integer> emptyColumns) {
        if (this.equals(other)) {
            return 0L;
        }

        int greaterX = Math.max(this.x, other.x);
        int greaterY = Math.max(this.y, other.y);
        int lowerX = Math.min(this.x, other.x);
        int lowerY = Math.min(this.y, other.y);

        long distance = (greaterX - lowerX) + (greaterY - lowerY);

        distance += emptyRows.stream()
                .filter(index -> index > lowerY && index < greaterY).count() * 999999;
        distance += emptyColumns.stream()
                .filter(index -> index > lowerX && index < greaterX).count() * 999999;

        return distance;
    }
}
