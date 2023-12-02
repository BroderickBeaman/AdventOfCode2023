import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {

    public static List<Object> loadInput() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(); //TODO: REMOVE
    }
}
