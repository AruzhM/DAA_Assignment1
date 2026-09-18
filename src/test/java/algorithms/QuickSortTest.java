package algorithms;

import metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortTest {
    @Test
    void testQuickSort() {
        int[] array = {6, 4, 5, 2, 4, 8, 4, 2, 7, 4};

        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{2, 2, 4, 4, 4, 4, 5, 6, 7, 8},
                array);
    }


    @Test
    void testDuplicates() {
        int[] array = {5, 5, 2, 8, 5, 1, 5, 2, 8};

        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{1, 2, 2, 5, 5, 5, 5, 8, 8},
                array
        );
    }

    @Test
    void testSortedArrayDepth() {
        int n = 100_000;
        int[] array = new int[n];

        for(int i = 0; i < n; i++) {
            array[i] = i;
        }
        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        int maxAllowedDepth =
                (int) (2 * (Math.log(n) / Math.log(2)));

        assertTrue(
                metrics.getMaxDepth() <= maxAllowedDepth,
                "Recursion depth is too large: " + metrics.getMaxDepth());


    }

}