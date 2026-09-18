package benchmark;

import algorithms.DeterministicSelect;
import algorithms.QuickSelect;
import metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class BonusBenchmark {

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    private static final int RUNS = 5;

    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("bonus_results.csv"))) {

            writer.println(
                    "algorithm,input,n,time_ms,comparisons"
            );

            for (int size : SIZES) {

                runBenchmark(
                        writer,
                        "random",
                        createRandomArray(size)
                );

                runBenchmark(
                        writer,
                        "sorted",
                        createSortedArray(size)
                );
            }

            System.out.println("Bonus benchmark completed.");
            System.out.println(
                    "Results saved to bonus_results.csv"
            );

        } catch (IOException e) {
            System.err.println(
                    "Could not write bonus_results.csv: "
                            + e.getMessage()
            );
        }
    }

    private static void runBenchmark(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        benchmarkQuickSelect(
                writer,
                inputType,
                originalArray
        );

        benchmarkDeterministicSelect(
                writer,
                inputType,
                originalArray
        );
    }

    private static void benchmarkQuickSelect(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];

        warmUpQuickSelect(originalArray);

        int k = originalArray.length / 2;

        for (int run = 0; run < RUNS; run++) {

            int[] array = Arrays.copyOf(
                    originalArray,
                    originalArray.length
            );

            Metrics metrics = new Metrics();

            QuickSelect.select(
                    array,
                    k,
                    metrics
            );

            times[run] = metrics.getTimeMillis();
            comparisons[run] = metrics.getComparisons();
        }

        writeResult(
                writer,
                "QuickSelect",
                inputType,
                originalArray.length,
                median(times),
                median(comparisons)
        );
    }

    private static void benchmarkDeterministicSelect(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];

        warmUpDeterministicSelect(originalArray);

        int k = originalArray.length / 2;

        for (int run = 0; run < RUNS; run++) {

            int[] array = Arrays.copyOf(
                    originalArray,
                    originalArray.length
            );

            Metrics metrics = new Metrics();

            DeterministicSelect.select(
                    array,
                    k,
                    metrics
            );

            times[run] = metrics.getTimeMillis();
            comparisons[run] = metrics.getComparisons();
        }

        writeResult(
                writer,
                "DeterministicSelect",
                inputType,
                originalArray.length,
                median(times),
                median(comparisons)
        );
    }

    private static void warmUpQuickSelect(
            int[] originalArray) {

        int[] array = Arrays.copyOf(
                originalArray,
                originalArray.length
        );

        QuickSelect.select(
                array,
                array.length / 2,
                new Metrics()
        );
    }

    private static void warmUpDeterministicSelect(
            int[] originalArray) {

        int[] array = Arrays.copyOf(
                originalArray,
                originalArray.length
        );

        DeterministicSelect.select(
                array,
                array.length / 2,
                new Metrics()
        );
    }

    private static void writeResult(
            PrintWriter writer,
            String algorithm,
            String inputType,
            int n,
            double time,
            long comparisons) {

        writer.printf(
                Locale.US,
                "%s,%s,%d,%.6f,%d%n",
                algorithm,
                inputType,
                n,
                time,
                comparisons
        );
    }

    private static double median(double[] values) {

        double[] copy = Arrays.copyOf(
                values,
                values.length
        );

        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static long median(long[] values) {

        long[] copy = Arrays.copyOf(
                values,
                values.length
        );

        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static int[] createRandomArray(int size) {

        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }

        return array;
    }

    private static int[] createSortedArray(int size) {

        int[] array = createRandomArray(size);

        Arrays.sort(array);

        return array;
    }
}