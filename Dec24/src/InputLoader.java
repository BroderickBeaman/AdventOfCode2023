import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<HailStone> loadHailStones() {
        List<String> allLines = loadLines();

        return allLines.stream().map(line -> {
            String[] halves = line.split(" @ ");
            assert halves.length == 2;

            String[] coordinates = halves[0].split(", ");
            assert coordinates.length == 3;
            Long x = Long.parseLong(coordinates[0]);
            Long y = Long.parseLong(coordinates[1]);
            Long z = Long.parseLong(coordinates[2]);

            String[] velocities = halves[1].split(", ");
            assert velocities.length == 3;
            Long dx = Long.parseLong(velocities[0]);
            Long dy = Long.parseLong(velocities[1]);
            Long dz = Long.parseLong(velocities[2]);

            return new HailStone(x, y, z, dx, dy, dz);
        }).toList();
    }
}
