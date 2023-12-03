public class NumberPosition {

    private String numberString;
    private int startIndex;

    private int lineLength;

    public NumberPosition(String numberString, int startIndex, int lineLength) {
        this.numberString = numberString;
        this.startIndex = startIndex;
        this.lineLength = lineLength;
    }

    public String getNumberString() {
        return numberString;
    }

    public int getAdjacentMin() {
        return Math.max(startIndex - 1, 0);
    }

    public int getAdjacentMax() {
        return Math.min(startIndex + numberString.length(), lineLength - 1);
    }

    public boolean isAdjacent(int symbolIndex) {
        boolean left = symbolIndex >= getAdjacentMin();
        boolean right = symbolIndex <= getAdjacentMax();
        return left && right;
    }
}
