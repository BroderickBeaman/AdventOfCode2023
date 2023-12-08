import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputLoader {

    private static final Pattern instructionPattern = Pattern.compile("[a-zA-Z0-9]+");

    public static Map<String, LeftRightInstruction> loadInstructions() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, LeftRightInstruction> instructionMap = new HashMap<>();

        for (String line : allLines) {
            Matcher instructionMatcher = instructionPattern.matcher(line);
            instructionMatcher.find();
            String key = instructionMatcher.group();
            instructionMatcher.find();
            String left = instructionMatcher.group();
            instructionMatcher.find();
            String right = instructionMatcher.group();

            instructionMap.put(key, new LeftRightInstruction(left, right));
        }

        return instructionMap;
    }
}
