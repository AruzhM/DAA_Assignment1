package algorithms;

import  metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length <= 1) {
            return;
        }

        metrics.startTimer();

        quickSort(array, 0, array.length - 1, metrics);

        metrics.stopTimer();
    }

    static int[] partition( int[] array, int left,  int right, int pivot,
            Metrics metrics) {

        int lt = left;
        int i = left;
        int gt = right;

        while (i <= gt) {
            metrics.incrementComparisons();

            if (array[i] < pivot) {
                int temp = array[lt];
                array[lt] = array[i];
                array[i] = temp;

                lt++;
                i++;

            } else if (array[i] > pivot) {
                int temp = array[i];
                array[i] = array[gt];
                array[gt] = temp;

                gt--;

            } else {
                i++;
            }
        }

        return new int[]{lt, gt};
    }


    private static void quickSort(
            int[] array, int left, int right, Metrics metrics) {

        metrics.enterRecursion();

        while (left < right) {
            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            int pivot = array[pivotIndex];

            int[] bounds = partition(array,left,right,pivot,metrics);

            int lt = bounds[0];
            int gt = bounds[1];


            int leftSize = lt - left;
            int rightSize = right - gt;

            if (leftSize < rightSize) {
                if (leftSize > 1) {
                    quickSort(array, left, lt - 1, metrics);
                }

                left = gt + 1;

            } else {
                if (rightSize > 1) {
                    quickSort(array, gt + 1, right, metrics);
                }

                right = lt - 1;
            }

        }
        metrics.exitRecursion();
    }

}



