import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GameLoader {

    public static List<Game> loadGames() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(line -> {

            String[] split1 = line.split(": ");
            Game game = new Game(Integer.parseInt(split1[0].split(" ")[1]));

            String[] arrayOfPulls = split1[1].split("; ");

            for(String pull : arrayOfPulls) {
                String[] diffColours = pull.split(", ");
                for (String colourPull : diffColours) {
                    String[] numAndColour = colourPull.split(" ");
                    Colour colour = Colour.fromLabel(numAndColour[1]);
                    switch (colour) {
                        case RED -> {
                            int num = Integer.parseInt(numAndColour[0]);
                            if (num > game.getRed()) {
                                game.setRed(num);
                            }
                        }
                        case GREEN -> {
                            int num = Integer.parseInt(numAndColour[0]);
                            if (num > game.getGreen()) {
                                game.setGreen(num);
                            }
                        }
                        case BLUE -> {
                            int num = Integer.parseInt(numAndColour[0]);
                            if (num > game.getBlue()) {
                                game.setBlue(num);
                            }
                        }
                    }
                }
            }

            return game;
        }).collect(Collectors.toList());
    }
}
