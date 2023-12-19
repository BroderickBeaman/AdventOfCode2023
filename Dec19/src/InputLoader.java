import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputLoader extends InputLoaderParent {

    public static Map<String, Workflow> loadWorkflows() {
        List<String> allLines = loadLines("workflows.txt");

        Map<String, Workflow> workflowMap = new HashMap<>();

        for (String line : allLines) {
            // Split away the label and the rest;
            // px AND a<2006:qkq,m>2090:A,rfg}
            String[] splits = line.split("\\{");

            String label = splits[0];

            // a<2006:qkq,m>2090:A,rfg
            String rules = splits[1].replace("}", "");

            String[] rulesArray = rules.split(",");

            Workflow workflow = new Workflow();
            for (String ruleString : rulesArray) {
                if (!ruleString.contains(":")) {
                    // Rule that will automatically pass
                    workflow.add(new Rule(ruleString));
                } else {
                    Character operand = ruleString.charAt(0);
                    Comparison comparison = Comparison.fromCharacter(ruleString.charAt(1));
                    int indexOfColon = ruleString.indexOf(':');
                    Long compareTo = Long.parseLong(ruleString.substring(2, indexOfColon));
                    String result = ruleString.split(":")[1];
                    workflow.add(new Rule(operand, comparison, compareTo, result));
                }
            }

            workflowMap.put(label, workflow);
        }

        return workflowMap;
    }

    public static List<Part> loadParts() {
        List<String> allLines = loadLines("parts.txt");

        return allLines.stream().map(line -> {
            // Remove outer brackets
            line = line.replace("{", "").replace("}", "");

            String[] split = line.split(",");
            assert split.length == 4;

            long x = Long.parseLong(split[0].split("=")[1]);
            long m = Long.parseLong(split[1].split("=")[1]);
            long a = Long.parseLong(split[2].split("=")[1]);
            long s = Long.parseLong(split[3].split("=")[1]);

            return new Part(x, m, a, s);
        }).toList();
    }
}
