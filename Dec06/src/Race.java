public class Race {

    private long time;

    private long distance;

    public Race(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public long getDistance() {
        return distance;
    }

    // I'm sure this can be optimized. But it works, and was still very fast
    // Given more time I would just find the first valid number, and then the last, and return the difference between
    // them
    public long getNumberOfWaysToWin() {
        boolean winning = false;
        long numWaysToWin = 0;
        for (long i = 1; i <= time; i++) {
            if (i * (time - i) > distance) {
                numWaysToWin++;
                winning = true;
                continue;
            }
            if (winning) {
                return numWaysToWin;
            }
        }
        return numWaysToWin;
    }
}
