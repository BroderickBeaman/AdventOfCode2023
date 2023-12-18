import java.util.ArrayList;
import java.util.List;

public class Dec18 {

    public static void main(String[] args) {
        List<Instruction> instructions = InputLoader.loadInstructionsPart2();

        Polygon polygon = getPolygon(instructions);

        long boundaryPoints = instructions.stream().mapToLong(Instruction::distance).sum();
        long shoelaceArea = polygon.shoelaceArea();
        // Pick's theorem
        long interiorPoints = shoelaceArea - (boundaryPoints/2) + 1;
        long numCubesFilled = interiorPoints + boundaryPoints;

        System.out.println("Number of cubes filled: " + numCubesFilled);
    }

    private static Polygon getPolygon(List<Instruction> instructions) {
        List<Point> points = new ArrayList<>();
        Point currentPoint = new Point(0L, 0L);

        for (Instruction instruction : instructions) {
            Long newRow = currentPoint.row() + (instruction.direction().row * instruction.distance());
            Long newCol = currentPoint.col() + (instruction.direction().col * instruction.distance());

            currentPoint = new Point(newRow, newCol);
            points.add(currentPoint);
        }

        return new Polygon(points);
    }
}
