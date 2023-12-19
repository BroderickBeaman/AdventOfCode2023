public record Part(long x, long m, long a, long s) {

    public long sum() {
        return x + m + a + s;
    }
}
