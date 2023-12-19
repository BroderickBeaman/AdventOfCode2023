import java.util.ArrayList;

public class Workflow extends ArrayList<Rule> {

    public String processRules(Part part) {
        for (Rule rule : this) {
            String result = rule.processRule(part);
            if (result != null) {
                return result;
            }
        }
        throw new RuntimeException("Workflow did not produce any results");
    }

}
