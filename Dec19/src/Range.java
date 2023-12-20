public record Range(Long min, Long max) {

    public Range {
        assert min <= max;
    }

    public boolean hasOverlap(Range other) {
        return this.min() <= other.max() && this.max() >= other.min();
    }

    public Range computeOverlap(Range other) {
        if (!hasOverlap(other)) {
            return null;
        }

        return new Range(Math.max(this.min(), other.min()), Math.min(this.max(), other.max()));
    }

    public long distance() {
        return max - min + 1;
    }
}
