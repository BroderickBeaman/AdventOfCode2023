import java.util.List;
import java.util.Map;

public class Dec19 {

    static Map<String, Workflow> workflowMap;

    public static void main(String[] args) {
        part1();
        System.out.println();
        part2();
    }

    private static void part2() {
        System.out.println("=== Part 2 ===");
        workflowMap = InputLoader.loadWorkflows();

        long start = System.currentTimeMillis();
        PartWithRanges partWithRanges = new PartWithRanges(
                new Range(1L, 4000L),
                new Range(1L, 4000L),
                new Range(1L, 4000L),
                new Range(1L, 4000L)
        );

        long total = computeTotal(partWithRanges, "in", 0);
        long end = System.currentTimeMillis();

        System.out.println("Total number of possibilities: " + total);
        System.out.println("Total execution time: " + (end - start) + "ms");
    }

    public static long computeTotal(PartWithRanges part, String label, int ruleIndex) {
        Workflow workflow = workflowMap.get(label);
        Rule rule = workflow.get(ruleIndex);
        String ruleResult = rule.result();
        // Base case. This rule has no condition. Either computed values or return 0 if rejected
        if (rule.operand() == null) {
            if (ruleResult.equals("A")) {
                return part.possibleValues();
            } else if (ruleResult.equals("R")) {
                return 0L;
            } else {
                return computeTotal(part, ruleResult, 0);
            }
        }

        long total = 0;

        PartWithRanges passesRule = rule.passesRule(part);
        PartWithRanges failsRule = rule.failsRule(part);

        if (passesRule.isValid()) {
            if (ruleResult.equals("A")) {
                total += passesRule.possibleValues();
            } else if (!ruleResult.equals("R")) {
                total += computeTotal(passesRule, ruleResult, 0);
            }
        }

        if (failsRule.isValid()) {
            total += computeTotal(failsRule, label, ruleIndex + 1);
        }

        return total;
    }

    private static void part1() {
        System.out.println("=== Part 1 ===");
        Map<String, Workflow> workflowMap = InputLoader.loadWorkflows();
        List<Part> parts = InputLoader.loadParts();

        long start = System.currentTimeMillis();
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
        long end = System.currentTimeMillis();

        System.out.println("Sum of accepted parts: " + sum);
        System.out.println("Total execution time: " + (end - start) + "ms");
    }
}
