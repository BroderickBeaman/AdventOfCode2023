import java.util.Objects;

public class GridState implements Comparable<GridState> {

    public final Integer heatLoss;
    public final Integer row;
    public final Integer col;
    public final Direction direction;
    public final Integer consecDirection;

    public GridState(int heatLoss, int row, int col, Direction direction, int consecDirection) {
        this.heatLoss = heatLoss;
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.consecDirection = consecDirection;
    }

    @Override
    public int compareTo(GridState o) {
        return this.heatLoss.compareTo(o.heatLoss);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridState gridState = (GridState) o;
        return Objects.equals(row, gridState.row) && Objects.equals(col, gridState.col) && direction == gridState.direction && Objects.equals(consecDirection, gridState.consecDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, direction, consecDirection);
    }
}
