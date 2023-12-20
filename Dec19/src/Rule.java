import java.util.List;

public record Rule(Character operand, Comparison comparison, Long compareTo, String result) {
    private static final List<Character> SUPPORTED_OPERANDS = List.of('x', 'm', 'a', 's');
    public static final long RANGE_MAX = 4000L;
    public static final long RANGE_MIN = 1L;

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

    public PartWithRanges passesRule(PartWithRanges partIn) {
        return switch (operand) {
            case 'x' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withX(partIn.x().computeOverlap(new Range(compareTo + 1, RANGE_MAX)));
                }
                yield partIn.withX(partIn.x().computeOverlap(new Range(RANGE_MIN, compareTo - 1)));
            }
            case 'm' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withM(partIn.m().computeOverlap(new Range(compareTo + 1, RANGE_MAX)));
                }
                yield partIn.withM(partIn.m().computeOverlap(new Range(RANGE_MIN, compareTo - 1)));
            }
            case 'a' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withA(partIn.a().computeOverlap(new Range(compareTo + 1, RANGE_MAX)));
                }
                yield partIn.withA(partIn.a().computeOverlap(new Range(RANGE_MIN, compareTo - 1)));
            }
            case 's' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withS(partIn.s().computeOverlap(new Range(compareTo + 1, RANGE_MAX)));
                }
                yield partIn.withS(partIn.s().computeOverlap(new Range(RANGE_MIN, compareTo - 1)));
            }
            default -> null;
        };
    }

    public PartWithRanges failsRule(PartWithRanges partIn) {
        return switch (operand) {
            case 'x' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withX(partIn.x().computeOverlap(new Range(RANGE_MIN, compareTo)));
                }
                yield partIn.withX(partIn.x().computeOverlap(new Range(compareTo, RANGE_MAX)));
            }
            case 'm' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withM(partIn.m().computeOverlap(new Range(RANGE_MIN, compareTo)));
                }
                yield partIn.withM(partIn.m().computeOverlap(new Range(compareTo, RANGE_MAX)));
            }
            case 'a' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withA(partIn.a().computeOverlap(new Range(RANGE_MIN, compareTo)));
                }
                yield partIn.withA(partIn.a().computeOverlap(new Range(compareTo, RANGE_MAX)));
            }
            case 's' -> {
                if (Comparison.GREATER_THAN.equals(comparison)) {
                    yield partIn.withS(partIn.s().computeOverlap(new Range(RANGE_MIN, compareTo)));
                }
                yield partIn.withS(partIn.s().computeOverlap(new Range(compareTo, RANGE_MAX)));
            }
            default -> null;
        };
    }

    public String processRule(Part part) {
        if (partPassesRule(part)) {
            return result;
        }
        return null;
    }
}
