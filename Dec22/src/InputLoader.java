import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Brick> loadBricks() {
        List<String> allLines = loadLines();

        return allLines.stream().map(line -> {
            String[] ends = line.split("~");
            assert ends.length == 2;

            String[] end1Strings = ends[0].split(",");
            assert end1Strings.length == 3;
            Point end1 = new Point(Integer.parseInt(end1Strings[0]),
                    Integer.parseInt(end1Strings[1]),
                    Integer.parseInt(end1Strings[2]));

            String[] end2Strings = ends[1].split(",");
            assert end2Strings.length == 3;
            Point end2 = new Point(Integer.parseInt(end2Strings[0]),
                    Integer.parseInt(end2Strings[1]),
                    Integer.parseInt(end2Strings[2]));

            return new Brick(end1, end2);
        }).toList();
    }
}
