package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testSimplePoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(10, 10)
        };

        double result = ClosestPair.findClosestDistance(points);

        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testDuplicatePoints() {
        Point[] points = {
                new Point(1, 1),
                new Point(5, 5),
                new Point(1, 1),
                new Point(10, 2)
        };

        double result = ClosestPair.findClosestDistance(points);

        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testAgainstBruteForce() {
        Random random = new Random(42);

        for (int test = 0; test < 20; test++) {

            int size = 2 + random.nextInt(1999);
            Point[] points = new Point[size];

            for (int i = 0; i < size; i++) {
                double x = random.nextDouble() * 2000 - 1000;
                double y = random.nextDouble() * 2000 - 1000;

                points[i] = new Point(x, y);
            }

            double expected = bruteForce(points);

            double actual =
                    ClosestPair.findClosestDistance(points);

            assertEquals(expected, actual, 1e-9);
        }
    }

    private static double bruteForce(Point[] points) {

        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1;
                 j < points.length;
                 j++) {

                double dx =
                        points[i].getX() - points[j].getX();

                double dy =
                        points[i].getY() - points[j].getY();

                double distance =
                        Math.sqrt(dx * dx + dy * dy);

                minDistance =
                        Math.min(minDistance, distance);
            }
        }

        return minDistance;
    }
}