# DAA Assignment 1 - Fast Sorting & Selection Engine

This project implements and evaluates several algorithms based on Divide and Conquer and Asymptotic Analysis.

## Implemented Algorithms

The project contains the following algorithms:

- MergeSort
- QuickSort
- QuickSelect

Additional components:

- Metrics for counting comparisons, measuring execution time, and tracking maximum recursion depth
- Benchmark for testing different input sizes and input types
- JUnit tests for algorithm correctness and edge cases

## Project Structure

```text
DAA_Assignment1
├── src
│   ├── algorithms
│   │   ├── MergeSort.java
│   │   ├── QuickSort.java
│   │   └── QuickSelect.java
│   │
│   ├── benchmark
│   │   └── Benchmark.java
│   │
│   └── metrics
│       └── Metrics.java
│
├── plots
│   ├── Time Vs N - Random.png
│   ├── Time vs n — sorted.png
│   ├── Time vs n — duplicates.png
│   ├── Recursion depth vs n — random.png
│   ├── Recursion depth vs n — sorted.png
│   ├── Recursion depth vs n — duplicates.png
│   ├── Ratio vs n — random.png
│   ├── Ratio vs n — sorted.png
│   └── Ratio vs n — duplicates.png
│
├── src/test
│   └── algorithms
│       ├── MergeSortTest.java
│       ├── QuickSortTest.java
│       ├── QuickSelectTest.java
│       └── AlgorithmCorrectnessTest.java
│
├── results.csv
├── REPORT.md
├── README.md
└── pom.xml
```

## Requirements

- Java JDK 25
- Maven
- JUnit 5
## How to Build

From the project root directory, run:

```bash
mvn clean package
```

## How to Run Tests

Run all JUnit tests with:

```bash
mvn test
```
## How to Run the Benchmark

Run the `Benchmark` class from IntelliJ IDEA.

The benchmark tests the following input sizes:

```text
1000
10000
100000
1000000
```
## Metrics

The `Metrics` class records:

- number of comparisons
- maximum recursion depth
- execution time measured using `System.nanoTime()`

The metrics object is passed explicitly to the algorithms, without using global metric counters.

## Algorithm Details

### MergeSort

MergeSort is implemented using the Divide and Conquer approach. A reusable buffer is allocated once at the top level and passed through the recursive calls. For subarrays of size 15 or less, Insertion Sort is used instead of further recursion. The merge step runs in linear time.

Expected complexity:

```text
Best Case:    Θ(n log n)
Average Case: Θ(n log n)
Worst Case:   Θ(n log n)
```

## Experimental Results

The benchmark results are stored in:

```text
results.csv
```
## Repository

The project is managed using Git.

The repository contains the following branches:

```text
main
feature/mergesort
feature/quicksort
feature/select
feature/metrics
```
## Bonus

The project can be extended with additional algorithms from the assignment:

- Median of Medians for deterministic linear-time selection
- Closest Pair of Points using Divide and Conquer

These extensions are optional and are not required for the main implementation.

## Conclusion

This project implements MergeSort, QuickSort, 
and QuickSelect and evaluates their practical 
performance on different input types and sizes. The benchmark results, comparison 
counts, and recursion depth are used to compare the experimental behavior with the expected asymptotic complexity. The complete analysis and experimental
results are presented in `REPORT.md`.





