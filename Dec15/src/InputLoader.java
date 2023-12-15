import java.util.Arrays;
import java.util.List;

public class InputLoader extends InputLoaderParent {
    public static List<String> loadInputs() {
        List<String> allLines = loadLines();
        assert allLines.size() == 1;

        return Arrays.asList(allLines.get(0).split(","));
    }
}
