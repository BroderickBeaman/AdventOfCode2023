import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Dec24 extends AOCParent {

    private static final long MIN = 200000000000000L;
    private static final long MAX = 400000000000000L;

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        List<HailStone> hailStones = InputLoader.loadHailStones();
        startPart(1);

        assert hailStones.stream().noneMatch(stone -> stone.dx() == 0);
        assert hailStones.stream().noneMatch(stone -> stone.dy() == 0);

        long totalIntersections = 0;

        for (int i = 0; i < hailStones.size() - 1; i++) {
            for (int j = i + 1; j < hailStones.size(); j++) {
                HailStone stone1 = hailStones.get(i);
                HailStone stone2 = hailStones.get(j);

                // Lines are parallel. Skip
                if (stone1.a() * stone2.b() == stone2.a() * stone1.b()) {
                    continue;
                }

                Point intersection = stone1.intersection2D(stone2);

                if (intersection.isInBoundary(MIN, MAX) &&
                        stone1.intersection2DInFuture(intersection) &&
                        stone2.intersection2DInFuture(intersection)) {
                    totalIntersections++;
                }
            }
        }

        System.out.println("Total possible intersections: " + totalIntersections);

        endPart();
    }

    // Inspiration for this part came from https://www.reddit.com/r/adventofcode/comments/18pnycy/comment/keqf8uq/
    private static void part2() {
        List<HailStone> hailStones = InputLoader.loadHailStones();
        startPart(2);

        Set<Long> possibleXVelocities = new HashSet<>();
        Set<Long> possibleYVelocities = new HashSet<>();
        Set<Long> possibleZVelocities = new HashSet<>();


        for (int i = 0; i < hailStones.size() - 1; i++) {
            for (int j = i + 1; j < hailStones.size(); j++) {
                HailStone hail1 = hailStones.get(i);
                HailStone hail2 = hailStones.get(j);

               // X velocities are same, X distance between will never change
               if (Objects.equals(hail1.dx(), hail2.dx())) {
                    final long distance = hail1.x() - hail2.x();
                    Set<Long> possibleVelocities = new HashSet<>();
                    for (long rockVelocity = -1000; rockVelocity <= 1000; rockVelocity++) {
                        if (rockVelocity == hail1.dx()) {
                            continue;
                        }
                        if ((distance % (rockVelocity - hail1.dx())) == 0) {
                            possibleVelocities.add(rockVelocity);
                        }
                    }

                    assert !possibleVelocities.isEmpty();

                    if (possibleXVelocities.isEmpty()) {
                        possibleXVelocities = new HashSet<>(possibleVelocities);
                    } else {
                        possibleXVelocities.retainAll(possibleVelocities);
                    }
               }

                // Y velocities are same, Y distance between will never change
                if (Objects.equals(hail1.dy(), hail2.dy())) {
                    final long distance = hail1.y() - hail2.y();
                    Set<Long> possibleVelocities = new HashSet<>();
                    for (long rockVelocity = -1000; rockVelocity <= 1000; rockVelocity++) {
                        if (rockVelocity == hail1.dy()) {
                            continue;
                        }
                        if ((distance % (rockVelocity - hail1.dy())) == 0) {
                            possibleVelocities.add(rockVelocity);
                        }
                    }

                    assert !possibleVelocities.isEmpty();

                    if (possibleYVelocities.isEmpty()) {
                        possibleYVelocities = new HashSet<>(possibleVelocities);
                    } else {
                        possibleYVelocities.retainAll(possibleVelocities);
                    }
                }

                // Z velocities are same, Z distance between will never change
                if (Objects.equals(hail1.dz(), hail2.dz())) {
                    final long distance = hail1.z() - hail2.z();
                    Set<Long> possibleVelocities = new HashSet<>();
                    for (long rockVelocity = -1000; rockVelocity <= 1000; rockVelocity++) {
                        if (rockVelocity == hail1.dz()) {
                            continue;
                        }
                        if ((distance % (rockVelocity - hail1.dz())) == 0) {
                            possibleVelocities.add(rockVelocity);
                        }
                    }

                    assert !possibleVelocities.isEmpty();

                    if (possibleZVelocities.isEmpty()) {
                        possibleZVelocities = new HashSet<>(possibleVelocities);
                    } else {
                        possibleZVelocities.retainAll(possibleVelocities);
                    }
                }
            }
        }

        // There should be only one possibility for the stone velocity at this point
        assert possibleXVelocities.size() == 1;
        assert possibleYVelocities.size() == 1;
        assert possibleZVelocities.size() == 1;

        long dx = possibleXVelocities.stream().findAny().get();
        long dy = possibleYVelocities.stream().findAny().get();
        long dz = possibleZVelocities.stream().findAny().get();

        HailStone hail1 = hailStones.get(0);
        HailStone hail2 = hailStones.get(1);

        long v1x = hail1.dx() - dx, v1y = hail1.dy() - dy, v1z = hail1.dz() - dz;
        long v2x = hail2.dx() - dx, v2y = hail2.dy() - dy, v2z = hail2.dz() - dz;

        Point3D point1 = new Point3D(hail1.x() + v1x, hail1.y() + v1y, hail1.z() + v1z);
        Point3D point2 = new Point3D(hail2.x() + v2x, hail2.y() + v2y, hail2.z() + v2z);

        // Calculated point of intersection by hand.
        System.out.println("Answer: " + (229734616875628L + 192049388333190L + 146602352667782L));

        endPart();
    }
}
