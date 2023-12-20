public record PartWithRanges(Range x, Range m, Range a, Range s) {

    public PartWithRanges withX(Range newX) {
        return new PartWithRanges(newX, m, a, s);
    }

    public PartWithRanges withM(Range newM) {
        return new PartWithRanges(x, newM, a, s);
    }

    public PartWithRanges withA(Range newA) {
        return new PartWithRanges(x, m, newA, s);
    }

    public PartWithRanges withS(Range newS) {
        return new PartWithRanges(x, m, a, newS);
    }

    public boolean isValid() {
        return x != null && m != null && a != null && s != null;
    }

    public long possibleValues() {
        return x.distance() * m.distance() * a.distance() * s.distance();
    }
}
