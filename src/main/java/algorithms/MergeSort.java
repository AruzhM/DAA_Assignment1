package algorithms;

import metrics.Metrics;

public class MergeSort {

    private static final int INSERTION_SORT_CUTOFF = 15;

    public static void sort(int[] array,Metrics metrics) {
        if (array == null || array.length <= 1 ) {
            return;
        }

        int[] buffer = new int[array.length];

        metrics.startTimer();

        mergeSort(array, buffer,0, array.length - 1, metrics);

        metrics.stopTimer();
    }


    private static void insertionSort(
            int[] array,
            int left,
            int right,
            Metrics metrics)     {

        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();

                if (array[j] <= key) {
                    break;
                }
                array[j+1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }


    private static void mergeSort(
            int[] array,
            int[] buffer,
            int left,
            int right,
            Metrics metrics) {

        metrics.enterRecursion();

        if (right - left + 1 <= INSERTION_SORT_CUTOFF) {
            insertionSort(array, left, right, metrics);
            metrics.exitRecursion();
            return;

        }
        int middle = left + (right - left) / 2;

        mergeSort(array, buffer, left, middle, metrics);
        mergeSort(array, buffer, middle + 1, right, metrics);

        merge(array, buffer, left, middle, right, metrics);

        metrics.exitRecursion();

    }

    private static void merge(
            int[] array,
            int[] buffer,
            int left,
            int middle,
            int right,
            Metrics metrics) {
        for (int i = left; i<= right; i++) {
            buffer[i] = array[i];
        }

        int i = left;
        int j = middle + 1;

        for (int k = left; k <= right; k++) {

            if (i > middle) {
                array[k] = buffer[j++];
            } else if (j > right) {
                array[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();

                if (buffer[i] <= buffer[j]) {
                    array[k] = buffer[i++];
                } else {
                    array[k] = buffer[j++];
                }



            }
        }


    }

}