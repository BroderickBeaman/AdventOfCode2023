import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Dec08 {

    public static void main(String[] args) {

        String instructions = "LRRLLRLLRRLRRLLRRLLRLRRRLLRRLRRRLRRLRRRLLRRLLRLLRRLRLRRRLRRLLRRRLRLRRLRRLRLRLRLLRLRRRLLRLLRRLRRRLRLRLRRRLRRLLRRRLRLRRLRRLLRRLRRRLRRLRRLRRLLRLRLRRLLRLLRRRLRRLRRLRRRLRLLRRRLRRRLRRLLRRRLRRRLRLLRLRRLRLLRRLLLRRLRRLRRLRLRRRLRRLLRLRRRLRRLRLLLRRLRRLRRRLLLRLLLLRRLRLLLRLRRRLRRRLRLRRRLLLLRLRRRLRLLLRRLRLRRLRRLRRRLRRRR";
        String[] instructionsArray = instructions.split("");
        int instructionLength = instructionsArray.length;
        Map<String, LeftRightInstruction> instructionMap = InputLoader.loadInstructions();

        List<String> allNodes = new ArrayList<>(instructionMap.keySet().stream()
                .filter(instruction -> instruction.endsWith("A"))
                .toList());


        List<Long> solveTimes = new ArrayList<>();
        for (String nodeString : allNodes) {
            String currentNode = nodeString;
            int numberOfInstructions = 0;
            for (boolean solved = false; !solved; numberOfInstructions++) {
                String instruction = instructionsArray[numberOfInstructions % instructionLength];
                LeftRightInstruction node = instructionMap.get(currentNode);
                if ("L".equals(instruction)) {
                    currentNode = node.getLeft();
                } else {
                    currentNode = node.getRight();
                }
                if (currentNode.endsWith("Z")) {
                    solved = true;
                }
            }
            solveTimes.add((long) numberOfInstructions);
        }

        //TODO: Implement least common multiple
        System.out.println("Number of instructions: ");
    }

    private static void part1(String[] instructionsArray, int instructionLength, Map<String, LeftRightInstruction> instructionMap) {
        int numberOfInstructions = 0;
        String currentNode = "AAA";
        for (boolean solved = false ; !solved; numberOfInstructions++ ) {
            String instruction = instructionsArray[numberOfInstructions% instructionLength];
            LeftRightInstruction node = instructionMap.get(currentNode);
            if ("L".equals(instruction)) {
                currentNode = node.getLeft();
            } else {
                currentNode = node.getRight();
            }

            if ("ZZZ".equals(currentNode)) {
                solved = true;
            }
        }

        System.out.println("Number of instructions: " + numberOfInstructions);
    }

}
