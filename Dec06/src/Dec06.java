import java.util.List;

public class Dec06 {

    public static void main(String[] args) {

//        List<Race> example = List.of(
//                new Race(7, 9),
//                new Race(15, 40),
//                new Race(30, 200)
//        );

        part2();

    }

    private static void part2() {
        Race race = new Race(51699878L, 377117112241505L);

        System.out.println("Ways to win the race: " + race.getNumberOfWaysToWin());
    }

    private static void part1() {
        List<Race> racesPart1 = List.of(
                new Race(51L, 377L),
                new Race(69L, 1171L),
                new Race(98L, 1224L),
                new Race(78L, 1505L)
        );

        long sum = racesPart1.stream()
                .mapToLong(Race::getNumberOfWaysToWin)
                .reduce(1, (a,b) -> a*b);

        System.out.println("Sum: " + sum);
    }
}
