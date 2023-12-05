public class SeedMap {

    private long destinationRangeStart;
    private long sourceRangeStart;

    private long sourceRangeEnd;
    private long rangeLength;
    private long sourceToDestDiff;

    public SeedMap(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;

        this.sourceRangeEnd = sourceRangeStart + rangeLength - 1;
        this.sourceToDestDiff = destinationRangeStart - sourceRangeStart;
    }

    public long getDestinationRangeStart() {
        return destinationRangeStart;
    }

    public long getSourceRangeStart() {
        return sourceRangeStart;
    }

    public long getRangeLength() {
        return rangeLength;
    }

    public long getSourceToDestDiff() {
        return sourceToDestDiff;
    }

    public boolean isInRange(Long seed) {
        return seed >= sourceRangeStart && seed <= sourceRangeEnd;
    }

    public Long computeDestValue(Long seed) {
        if (isInRange(seed)) {
            return seed + sourceToDestDiff;
        }
        return seed;
    }
}
