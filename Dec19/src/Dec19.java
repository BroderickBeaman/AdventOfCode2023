import java.util.List;
import java.util.Map;

public class Dec19 {

    public static void main(String[] args) {
        Map<String, Workflow> workflowMap = InputLoader.loadWorkflows();
        List<Part> parts = InputLoader.loadParts();

        long sum = parts.stream().mapToLong(part -> {
            String label = "in";
            while(true) {
                String result = workflowMap.get(label).processRules(part);
                if (result.equals("A")) {
                    return part.sum();
                } else if (result.equals("R")) {
                    return 0;
                }
                label = result;
            }
        }).sum();

        System.out.println("Sum of accepted parts: " + sum);
    }
}
