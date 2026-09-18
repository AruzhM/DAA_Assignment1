package algorithms;

import metrics.Metrics;

public class DeterministicSelect {

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

        int result = select(
                array,
                0,
                array.length - 1,
                k,
                metrics
        );

        metrics.stopTimer();

        return result;
    }

    private static int select(
            int[] array,
            int left,
            int right,
            int k,
            Metrics metrics) {

        metrics.enterRecursion();

        int size = right - left + 1;

        if (size <= 5) {
            insertionSort(array, left, right, metrics);

            int result = array[k];

            metrics.exitRecursion();
            return result;
        }

        int pivot = choosePivot(array, left, right, metrics);

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
            result = select(
                    array,
                    left,
                    lt - 1,
                    k,
                    metrics
            );
        } else if (k > gt) {
            result = select(
                    array,
                    gt + 1,
                    right,
                    k,
                    metrics
            );
        } else {
            result = array[k];
        }

        metrics.exitRecursion();
        return result;
    }

    private static int choosePivot(
            int[] array,
            int left,
            int right,
            Metrics metrics) {

        int size = right - left + 1;
        int groups = (size + 4) / 5;

        int[] medians = new int[groups];

        for (int group = 0; group < groups; group++) {

            int groupLeft = left + group * 5;
            int groupRight = Math.min(groupLeft + 4, right);

            insertionSort(
                    array,
                    groupLeft,
                    groupRight,
                    metrics
            );

            int middle = groupLeft
                    + (groupRight - groupLeft) / 2;

            medians[group] = array[middle];
        }

        int medianIndex = medians.length / 2;

        return select(
                medians,
                0,
                medians.length - 1,
                medianIndex,
                metrics
        );
    }

    private static void insertionSort(
            int[] array,
            int left,
            int right,
            Metrics metrics) {

        for (int i = left + 1; i <= right; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= left) {

                metrics.incrementComparisons();

                if (array[j] <= key) {
                    break;
                }

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
}