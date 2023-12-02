import java.util.List;

public class Dec02 {

    public static void main(String[] args) {
        part2();
    }

    private static void part1() {
        List<Game> games = GameLoader.loadGames();

        int sum = games.stream()
                .filter(Game::isGameValid)
                .mapToInt(Game::getId)
                .sum();

        System.out.println("Sum of game ids: " + sum);
    }

    private static void part2() {
        List<Game> games = GameLoader.loadGames();

        int sum = games.stream()
                .mapToInt(Game::getPower)
                .sum();

        System.out.println("Sum of game ids: " + sum);
    }
}
