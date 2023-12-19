import java.util.List;

public record Rule(Character operand, Comparison comparison, Long compareTo, String result) {
    private static final List<Character> SUPPORTED_OPERANDS = List.of('x', 'm', 'a', 's');

    public Rule {
        assert operand == null || (SUPPORTED_OPERANDS.contains(operand) && comparison != null && compareTo != null);
        assert result != null;
    }

    public Rule(String result) {
        this(null, null, null, result);
    }

    public boolean partPassesRule(Part part) {
        if (operand == null) {
            return true;
        }

        return switch (operand) {
            case 'x' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield part.x() > compareTo;
                }
                yield part.x() < compareTo;
            }
            case 'm' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield part.m() > compareTo;
                }
                yield part.m() < compareTo;
            }
            case 'a' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield part.a() > compareTo;
                }
                yield part.a() < compareTo;
            }
            case 's' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield part.s() > compareTo;
                }
                yield part.s() < compareTo;
            }
            default -> false;
        };
    }

    public String processRule(Part part) {
        if (partPassesRule(part)) {
            return result;
        }
        return null;
    }
}
