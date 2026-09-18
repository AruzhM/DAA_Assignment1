package algorithms;

import metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmCorrectnessTest {
    @Test
    void testMergeSortRA() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = 1 + random.nextInt(100);
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int[] expected = Arrays.copyOf(array, array.length);
            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            MergeSort.sort(array, metrics);

            assertArrayEquals(expected, array);
        }
    }

    @Test
    void testSortEdgeCases() {
        Metrics metrics1 = new Metrics();
        int[] oneElement = {42};
        MergeSort.sort(oneElement, metrics1);
        assertArrayEquals(new int[]{42}, oneElement);

        Metrics metrics2 = new Metrics();
        int[] sorted = {1, 2, 3, 4, 5};
        QuickSort.sort(sorted, metrics2);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);

        Metrics metrics3 = new Metrics();
        int[] equal = {7, 7, 7, 7, 7};
        QuickSort.sort(equal, metrics3);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equal);

        Metrics metrics4 = new Metrics();
        int[] empty = {};
        MergeSort.sort(empty, metrics4);
        assertArrayEquals(new int[]{}, empty);

    }

    @Test
    void testQuickSelectEdgeCases() {
        Metrics metrics1 = new Metrics();
        assertEquals(
                42,
                QuickSelect.select(new int[]{42}, 0, metrics1)
        );

        Metrics metrics2 = new Metrics();
        assertEquals(
                5,
                QuickSelect.select(
                        new int[]{1, 2, 3, 4, 5},
                        4,
                        metrics2
                )
        );

        Metrics metrics3 = new Metrics();
        assertEquals(
                1,
                QuickSelect.select(
                        new int[]{1, 2, 3, 4, 5},
                        0,
                        metrics3
                )
        );
    }

    @Test
    void testQuickSortRA() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {

            int size = 1 + random.nextInt(100);
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int[] expected = Arrays.copyOf(array, array.length);
            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            QuickSort.sort(array, metrics);

            assertArrayEquals(expected, array);
        }
    }


        @Test
        void testQuickSelectRA () {
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

                int result = QuickSelect.select(array, k, metrics);

                assertEquals(sorted[k], result);
            }
        }
    }
