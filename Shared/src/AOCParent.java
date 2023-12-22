public class AOCParent {

    private static long start;

    public static void startPart(int partNumber) {
        System.out.println("=== Part " + partNumber + " ===");
        start = System.currentTimeMillis();
    }

    public static void endPart() {
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start) + "ms");
        System.out.println();
    }
}
