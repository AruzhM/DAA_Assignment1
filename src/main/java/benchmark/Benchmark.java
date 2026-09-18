package benchmark;
import java.util.Locale;
import algorithms.MergeSort;
import algorithms.QuickSelect;
import algorithms.QuickSort;
import metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

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
                new FileWriter("results.csv"))) {

            writer.println(
                    "algorithm,input,n,time_ms,comparisons,max_depth"
            );

            for (int size : SIZES) {
                runBenchmark(writer, "random", createRandomArray(size));
                runBenchmark(writer, "sorted", createSortedArray(size));
                runBenchmark(writer, "duplicates",
                        createDuplicatesArray(size));
            }

            System.out.println("Benchmark completed.");
            System.out.println("Results saved to results.csv");

        } catch (IOException e) {
            System.err.println("Could not write results.csv: "
                    + e.getMessage());
        }
    }

    private static void runBenchmark(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        benchmarkMergeSort(writer, inputType, originalArray);
        benchmarkQuickSort(writer, inputType, originalArray);
        benchmarkQuickSelect(writer, inputType, originalArray);
    }

    private static void benchmarkMergeSort(
            PrintWriter writer,
            String inputType,
            int[] originalArray
    ) {
        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        warmUpMergeSort(originalArray);

        for (int run = 0; run < RUNS; run++) {
            int[] array = Arrays.copyOf(
                    originalArray,
                    originalArray.length
            );

            Metrics metrics = new Metrics();

            MergeSort.sort(array, metrics);

            times[run] = metrics.getTimeMillis();
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        writeResult(
                writer,
                "MergeSort",
                inputType,
                originalArray.length,
                median(times),
                median(comparisons),
                median(depths)
        );
    }

    private static void benchmarkQuickSort(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        warmUpQuickSort(originalArray);

        for (int run = 0; run < RUNS; run++) {
            int[] array = Arrays.copyOf(
                    originalArray,
                    originalArray.length
            );

            Metrics metrics = new Metrics();

            QuickSort.sort(array, metrics);

            times[run] = metrics.getTimeMillis();
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        writeResult(
                writer,
                "QuickSort",
                inputType,
                originalArray.length,
                median(times),
                median(comparisons),
                median(depths)
        );
    }

    private static void benchmarkQuickSelect(
            PrintWriter writer,
            String inputType,
            int[] originalArray) {

        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        warmUpQuickSelect(originalArray);

        int k = originalArray.length / 2;

        for (int run = 0; run < RUNS; run++) {
            int[] array = Arrays.copyOf(
                    originalArray,
                    originalArray.length
            );

            Metrics metrics = new Metrics();

            QuickSelect.select(array, k, metrics);

            times[run] = metrics.getTimeMillis();
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        writeResult(
                writer,
                "QuickSelect",
                inputType,
                originalArray.length,
                median(times),
                median(comparisons),
                median(depths)
        );
    }


    private static void warmUpMergeSort(int[] originalArray) {
        int[] array = Arrays.copyOf(
                originalArray,
                originalArray.length);

        MergeSort.sort(array, new Metrics());
    }

    private static void warmUpQuickSort(int[] originalArray) {
        int[] array = Arrays.copyOf(
                originalArray,
                originalArray.length
        );

        QuickSort.sort(array, new Metrics());
    }
    private static void warmUpQuickSelect(int[] originalArray) {
        int[] array = Arrays.copyOf(
                originalArray,
                originalArray.length
        );

        int k = array.length / 2;

        QuickSelect.select(array, k, new Metrics());
    }

    private static void writeResult(
            PrintWriter writer,
            String algorithm,
            String inputType,
            int n,
            double time,
            long comparisons,
            int maxDepth) {

        writer.printf(
                Locale.US,
                "%s,%s,%d,%.6f,%d,%d%n",
                algorithm,
                inputType,
                n,
                time,
                comparisons,
                maxDepth
        );
    }

    private static double median(double[] values) {
        double[] copy = Arrays.copyOf(values, values.length);
        Arrays.sort(copy);

        int middle = copy.length / 2;

        if (copy.length % 2 == 1) {
            return copy[middle];
        }

        return (copy[middle - 1] + copy[middle]) / 2.0;
    }

    private static long median(long[] values) {
        long[] copy = Arrays.copyOf(values, values.length);
        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static int median(int[] values) {
        int[] copy = Arrays.copyOf(values, values.length);
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

    private static int[] createDuplicatesArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(10);
        }

        return array;
    }
}






