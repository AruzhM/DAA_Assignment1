package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosestDistance(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException(
                    "At least two points are required"
            );
        }

        Point[] pointsByX = Arrays.copyOf(
                points,
                points.length
        );

        Point[] pointsByY = Arrays.copyOf(
                points,
                points.length
        );

        Arrays.sort(
                pointsByX,
                Comparator.comparingDouble(Point::getX)
        );

        Arrays.sort(
                pointsByY,
                Comparator.comparingDouble(Point::getY)
        );

        return closest(
                pointsByX,
                pointsByY
        );
    }

    private static double closest(
            Point[] pointsByX,
            Point[] pointsByY) {

        int n = pointsByX.length;

        if (n <= 3) {
            return bruteForce(pointsByX);
        }

        int middle = n / 2;
        Point middlePoint = pointsByX[middle];

        Point[] leftByX = Arrays.copyOfRange(
                pointsByX,
                0,
                middle
        );

        Point[] rightByX = Arrays.copyOfRange(
                pointsByX,
                middle,
                n
        );

        Point[] leftByY = new Point[leftByX.length];
        Point[] rightByY = new Point[rightByX.length];

        int leftCount = 0;
        int rightCount = 0;

        java.util.Set<Point> leftPoints =
                new java.util.HashSet<>(
                        Arrays.asList(leftByX)
                );

        for (Point point : pointsByY) {
            if (leftPoints.contains(point)) {
                leftByY[leftCount++] = point;
            } else {
                rightByY[rightCount++] = point;
            }
        }

        double leftDistance = closest(
                leftByX,
                leftByY
        );

        double rightDistance = closest(
                rightByX,
                rightByY
        );

        double delta = Math.min(
                leftDistance,
                rightDistance
        );

        Point[] strip = new Point[n];
        int stripSize = 0;

        for (Point point : pointsByY) {
            if (Math.abs(
                    point.getX() - middlePoint.getX()
            ) < delta) {
                strip[stripSize++] = point;
            }
        }

        for (int i = 0; i < stripSize; i++) {

            for (int j = i + 1;
                 j < stripSize && j <= i + 7;
                 j++) {

                double distance = distance(
                        strip[i],
                        strip[j]
                );

                delta = Math.min(delta, distance);
            }
        }

        return delta;
    }

    private static double bruteForce(Point[] points) {

        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1;
                 j < points.length;
                 j++) {

                minDistance = Math.min(
                        minDistance,
                        distance(points[i], points[j])
                );
            }
        }

        return minDistance;
    }

    private static double distance(
            Point first,
            Point second) {

        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();

        return Math.sqrt(
                dx * dx + dy * dy
        );
    }
}