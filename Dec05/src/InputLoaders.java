import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class InputLoaders {

    public static List<Long> loadSeeds() {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get("resources/seeds.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Long> seedList = new ArrayList<>();

        String[] numbers = allLines.get(0).split(" ");

        for (int i = 0; i < numbers.length; i = i + 2) {
            Long rangeStart = Long.parseLong(numbers[i]);
            Long rangeLength = Long.parseLong(numbers[i + 1]);

            seedList.addAll(LongStream.range(rangeStart, rangeStart + rangeLength - 1)
                    .boxed()
                    .toList());
        }

        return seedList;
    }

    public static List<SeedMap> loadSeedToSoil() {
        return loadMap("resources/seedToSoil.txt");
    }

    public static List<SeedMap> loadSoilToFertilizer() {
        return loadMap("resources/soilToFertilizer.txt");
    }

    public static List<SeedMap> loadFertilizerToWater() {
        return loadMap("resources/fertilizerToWater.txt");
    }

    public static List<SeedMap> loadWaterToLight() {
        return loadMap("resources/waterToLight.txt");
    }

    public static List<SeedMap> loadLightToTemperature() {
        return loadMap("resources/lightToTemperature.txt");
    }

    public static List<SeedMap> loadTemperatureToHumidity() {
        return loadMap("resources/temperatureToHumidity.txt");
    }

    public static List<SeedMap> loadHumidityToLocation() {
        return loadMap("resources/humidityToLocation.txt");
    }

    public static List<SeedMap> loadMap(String fileName) {
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allLines.stream().map(line -> {
            String[] numbers =  line.split(" ");
            return new SeedMap(Long.parseLong(numbers[0]), Long.parseLong(numbers[1]), Long.parseLong(numbers[2]));
        }).toList();
    }
}
