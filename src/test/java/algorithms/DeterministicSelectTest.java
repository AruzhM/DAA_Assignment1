package algorithms;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeterministicSelectTest {

    @Test
    void testDeterministicSelectRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = 1 + random.nextInt(100);
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int k = random.nextInt(size);

            int[] sorted = Arrays.copyOf(array, array.length);
            Arrays.sort(sorted);

            Metrics metrics = new Metrics();

            int result = DeterministicSelect.select(
                    array,
                    k,
                    metrics
            );

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testSortedArray() {
        int[] array = new int[1000];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Metrics metrics = new Metrics();

        int result = DeterministicSelect.select(
                array,
                500,
                metrics
        );

        assertEquals(500, result);
    }

    @Test
    void testInvalidK() {
        int[] array = {1, 2, 3};

        Metrics metrics = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> DeterministicSelect.select(
                        array,
                        3,
                        metrics
                )
        );
    }
}