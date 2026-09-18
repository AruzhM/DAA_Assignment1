package algorithms;
import metrics.Metrics;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MergeSortTest {

    @Test
    void testMergeSort() {
        int[] array = { 6, 3, 9, 2, 4};

        Metrics metrics = new Metrics();

        MergeSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{2, 3, 4, 6, 9},
                array
        );


    }
}