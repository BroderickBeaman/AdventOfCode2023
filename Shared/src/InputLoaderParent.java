import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputLoaderParent {

    public static List<String> loadLines() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines;
    }
}
