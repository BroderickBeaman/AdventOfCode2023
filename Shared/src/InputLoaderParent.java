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

    public static List<String> loadLines(String fileName) {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines;
    }
}
