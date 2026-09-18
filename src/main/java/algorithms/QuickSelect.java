package algorithms;

import metrics.Metrics;
import java.util.Random;

public class QuickSelect {

    private static final Random RANDOM = new Random();

    public static int select(int[] array, int k, Metrics metrics) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException(
                    "k is out of range: " + k
            );
        }

        metrics.startTimer();

        int result = quickSelect(
                array,
                0,
                array.length - 1,
                k,
                metrics);

        metrics.stopTimer();
        return result;
    }
    private static int quickSelect(
            int[] array,
            int left,
            int right,
            int k,
            Metrics metrics) {

        metrics.enterRecursion();

        int pivotIndex = left + RANDOM.nextInt(right - left + 1);
        int pivot = array[pivotIndex];

        int[] bounds = QuickSort.partition(
                array,
                left,
                right,
                pivot,
                metrics
        );

        int lt = bounds[0];
        int gt = bounds[1];

        int result;

        if (k < lt) {
            result = quickSelect(array, left, lt - 1, k, metrics);

        } else if (k > gt) {
            result = quickSelect(array, gt + 1, right, k, metrics);

        } else {
            result = array[k];
        }

        metrics.exitRecursion();

        return result;
    }
}
