import java.util.List;
import java.util.OptionalLong;

public class Dec05 {

    public static void main(String[] args) {
        List<SeedMap> seedToSoil = InputLoaders.loadSeedToSoil();
        List<SeedMap> soilToFertilizer = InputLoaders.loadSoilToFertilizer();
        List<SeedMap> fertilizerToWater = InputLoaders.loadFertilizerToWater();
        List<SeedMap> waterToLight = InputLoaders.loadWaterToLight();
        List<SeedMap> lightToTemp = InputLoaders.loadLightToTemperature();
        List<SeedMap> tempToHumidity = InputLoaders.loadTemperatureToHumidity();
        List<SeedMap> humidityToLocation = InputLoaders.loadHumidityToLocation();

        Long startSeed = 1830497666L;
        Long seedRange = 190544464L;

        Long minLocation = null;

        for (long l = startSeed; l < startSeed + seedRange; l++) {
            List<Long> seedList = List.of(l);
            Long location = seedList.stream()
                    .map(seed -> {
                        for (SeedMap map : seedToSoil) {
                            if (map.isInRange(seed)) {
                                return map.computeDestValue(seed);
                            }
                        }
                        return seed;
                    }).map(soil -> {
                        for (SeedMap map : soilToFertilizer) {
                            if (map.isInRange(soil)) {
                                return map.computeDestValue(soil);
                            }
                        }
                        return soil;
                    }).map(fertilizer -> {
                        for (SeedMap map : fertilizerToWater) {
                            if (map.isInRange(fertilizer)) {
                                return map.computeDestValue(fertilizer);
                            }
                        }
                        return fertilizer;
                    }).map(water -> {
                        for (SeedMap map : waterToLight) {
                            if (map.isInRange(water)) {
                                return map.computeDestValue(water);
                            }
                        }
                        return water;
                    }).map(light -> {
                        for (SeedMap map : lightToTemp) {
                            if (map.isInRange(light)) {
                                return map.computeDestValue(light);
                            }
                        }
                        return light;
                    }).map(temp -> {
                        for (SeedMap map : tempToHumidity) {
                            if (map.isInRange(temp)) {
                                return map.computeDestValue(temp);
                            }
                        }
                        return temp;
                    }).map(humidity -> {
                        for (SeedMap map : humidityToLocation) {
                            if (map.isInRange(humidity)) {
                                return map.computeDestValue(humidity);
                            }
                        }
                        return humidity;
                    }).toList().get(0);

            if (minLocation == null || location < minLocation) {
                minLocation = location;
            }
        }

        System.out.println("Min location: " + minLocation);
    }

    private static void part1() {
        List<Long> seeds = InputLoaders.loadSeeds();
        List<SeedMap> seedToSoil = InputLoaders.loadSeedToSoil();
        List<SeedMap> soilToFertilizer = InputLoaders.loadSoilToFertilizer();
        List<SeedMap> fertilizerToWater = InputLoaders.loadFertilizerToWater();
        List<SeedMap> waterToLight = InputLoaders.loadWaterToLight();
        List<SeedMap> lightToTemp = InputLoaders.loadLightToTemperature();
        List<SeedMap> tempToHumidity = InputLoaders.loadTemperatureToHumidity();
        List<SeedMap> humidityToLocation = InputLoaders.loadHumidityToLocation();

        OptionalLong minLocation = seeds.stream()
                .map(seed -> {
                    for (SeedMap map : seedToSoil) {
                        if (map.isInRange(seed)) {
                            return map.computeDestValue(seed);
                        }
                    }
                    return seed;
                }).map(soil -> {
                    for (SeedMap map : soilToFertilizer) {
                        if (map.isInRange(soil)) {
                            return map.computeDestValue(soil);
                        }
                    }
                    return soil;
                }).map(fertilizer -> {
                    for (SeedMap map : fertilizerToWater) {
                        if (map.isInRange(fertilizer)) {
                            return map.computeDestValue(fertilizer);
                        }
                    }
                    return fertilizer;
                }).map(water -> {
                    for (SeedMap map : waterToLight) {
                        if (map.isInRange(water)) {
                            return map.computeDestValue(water);
                        }
                    }
                    return water;
                }).map(light -> {
                    for (SeedMap map : lightToTemp) {
                        if (map.isInRange(light)) {
                            return map.computeDestValue(light);
                        }
                    }
                    return light;
                }).map(temp -> {
                    for (SeedMap map : tempToHumidity) {
                        if (map.isInRange(temp)) {
                            return map.computeDestValue(temp);
                        }
                    }
                    return temp;
                }).map(humidity -> {
                    for (SeedMap map : humidityToLocation) {
                        if (map.isInRange(humidity)) {
                            return map.computeDestValue(humidity);
                        }
                    }
                    return humidity;
                }).mapToLong(Long::longValue).min();

        System.out.println("Min location: " + minLocation.getAsLong());
    }

}
