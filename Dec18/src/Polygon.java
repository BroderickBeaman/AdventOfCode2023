import java.util.List;

public class Polygon {
    private final List<Point> points;
    public final Long minRow;
    public final Long maxRow;
    public final Long minCol;
    public final Long maxCol;

    public Polygon(List<Point> points) {
        this.points = points;
        long minRow = 0L, maxRow = 0L, minCol = 0L, maxCol = 0L;
        for (Point point : points) {
            minRow = Math.min(minRow, point.row());
            maxRow = Math.max(maxRow, point.row());
            minCol = Math.min(minCol, point.col());
            maxCol = Math.max(maxCol, point.col());
        }

        this.minRow = minRow;
        this.maxRow = maxRow;
        this.minCol = minCol;
        this.maxCol = maxCol;
    }

    public long shoelaceArea() {
        int n = points.size();
        long area = 0L;
        for (int i = 0; i < n - 1; i++) {
            area += points.get(i).col() * points.get(i + 1).row() - points.get(i + 1).col() * points.get(i).row();
        }
        return (Math.abs(area + points.get(n - 1).col() * points.get(0).row() - points.get(0).col() * points.get(n - 1).row()) / 2);
    }
}
