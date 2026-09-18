## 1. Introduction

This assignment focuses on the implementation and analysis of Divide-and-Conquer
algorithms for processing integer arrays. The main objective is to implement
MergeSort, QuickSort, and QuickSelect in Java and evaluate their performance
on different types and sizes of input data.

The algorithms were tested using random, sorted, and duplicate-heavy arrays
with sizes ranging from 1,000 to 1,000,000 elements. During the experiments,
execution time, the number of comparisons, and maximum recursion depth were
measured. The collected results were saved to a CSV file and used to create
plots for further analysis.

In addition to implementation and testing, the assignment examines the
theoretical complexity of the algorithms using O, Ω, and Θ notations and
recurrence relations. The experimental results are then compared with the
expected theoretical behavior to identify how closely the practical
performance follows the asymptotic analysis.

## 2. Asymptotic Bounds

The time complexity of the implemented algorithms depends on the structure
of the input and on how the algorithms divide the problem into smaller
subproblems. The following table summarizes the best, average, and worst-case
complexities of the algorithms used in this assignment.

| Algorithm | Best Case | Average Case | Worst Case |
|---|---|---|---|
| MergeSort | Θ(n log n) — the array is divided into smaller parts and the results are merged | Θ(n log n) — both halves are recursively sorted and merged | Θ(n log n) — the same divide-and-merge process is required for any input |
| QuickSort | Θ(n log n) — the pivot produces approximately balanced partitions | Θ(n log n) — random pivot selection gives balanced partitions on average | O(n²) — the pivot repeatedly produces very unbalanced partitions |
| QuickSelect | Θ(n) — a favorable pivot can quickly reduce the problem size | Θ(n) — only one partition is processed at each step | O(n²) — repeated unbalanced partitions can occur |
| Insertion Sort | Θ(n) — the elements are already in sorted order | Θ(n²) — elements usually need to be shifted during insertion | O(n²) — reverse-sorted input causes the maximum number of shifts |

MergeSort has a Θ(n log n) complexity in all three cases because it always
divides the array recursively and performs linear-time merging at each level.
QuickSort can achieve Θ(n log n) when the partitions are balanced, while very
unbalanced partitions can lead to O(n²) time. QuickSelect is expected to be
linear because it continues only in the partition that contains the required
element. Insertion Sort is efficient for already sorted small subarrays,
which is why it is used as the cutoff method in the MergeSort implementation.

## 3. Recurrence Analysis

### 3.1 MergeSort

For MergeSort, the array is divided into two equal parts, both parts are
sorted recursively, and then they are merged.

The recurrence is:

`T(n) = 2T(n/2) + Θ(n)`

Here, `a = 2`, `b = 2`, and `f(n) = Θ(n)`.

We calculate:

`n^(log_b(a)) = n^(log_2(2)) = n`

Since `f(n) = Θ(n)`, this is Master Theorem Case 2.

Therefore:

`T(n) = Θ(n log n)`

The implementation uses Insertion Sort for subarrays containing 15 or fewer
elements. This reduces the overhead for small subproblems but does not
change the overall asymptotic complexity.

### 3.2 QuickSort

For the recurrence analysis, we assume that the pivot divides the array into
two balanced parts.

The recurrence is:

`T(n) = 2T(n/2) + Θ(n)`

Here, `a = 2`, `b = 2`, and `f(n) = Θ(n)`.

Therefore, this is Master Theorem Case 2:

`T(n) = Θ(n log n)`

In the implementation, the pivot is selected randomly. A random pivot does
not guarantee a balanced partition every time, but it reduces the chance of
repeatedly choosing very unbalanced partitions. Therefore, QuickSort has
`O(n log n)` expected running time.

The implementation also processes the larger partition iteratively and
recurses only into the smaller partition. This helps keep the recursion
depth bounded.

### 3.3 QuickSelect

For QuickSelect, we assume a balanced partition.

The recurrence is:

`T(n) = T(n/2) + Θ(n)`

Here, `a = 1`, `b = 2`, and `f(n) = Θ(n)`.

We calculate:

`n^(log_b(a)) = n^(log_2(1)) = 1`

Since `f(n) = Θ(n)` grows asymptotically faster than `1`, this is Master
Theorem Case 3.

Therefore:

`T(n) = Θ(n)`

The main difference from QuickSort is that QuickSelect does not process both
partitions. After partitioning, it continues only in the part that contains
the required position `k`.

## 4. Experimental Setup

The experiments were performed using three algorithms: MergeSort, QuickSort,
and QuickSelect. The algorithms were tested on four different input sizes:
1,000, 10,000, 100,000, and 1,000,000 elements.

Three types of input arrays were used. The first type was a random array
containing random integer values. The second type was a sorted array. The
third type contained many duplicate values, with each value generated from
the range 0 to 9.

For each combination of algorithm, input type, and array size, the benchmark
was executed five times. A warm-up run was performed before the measured
runs because the first executions can be affected by JVM warm-up. A new copy
of the input array was created for each run so that all runs used the same
initial data.

The benchmark measured three performance metrics: execution time in
milliseconds, the number of comparisons, and the maximum recursion depth.
The execution time was measured using `System.nanoTime()`. After five runs,
the median value was stored in the results.

The benchmark results were exported to `results.csv` using the following
columns:

`algorithm,input,n,time_ms,comparisons,max_depth`



## 5. Benchmark Results

At `n = 1,000,000`, the measured median execution times were:

| Input | MergeSort | QuickSort | QuickSelect |
|---|---:|---:|---:|
| Random | 118.0355 ms | 121.7619 ms | 15.6435 ms |
| Sorted | 26.0362 ms | 59.5097 ms | 3.0985 ms |
| Duplicates | 54.6811 ms | 15.1304 ms | 10.9451 ms |

The results show that QuickSelect requires less time than the sorting
algorithms because it only searches for one required position instead of
sorting the entire array. The input type also affects the measured
performance of QuickSort. For duplicate-heavy input, QuickSort has a much
smaller recursion depth because the three-way partition groups equal
elements together.

At `n = 1,000,000`, the recorded maximum recursion depths were:

| Input | MergeSort | QuickSort | QuickSelect |
|---|---:|---:|---:|
| Random | 18 | 13 | 28 |
| Sorted | 18 | 13 | 27 |
| Duplicates | 18 | 2 | 4 |

MergeSort has a relatively stable recursion depth because its recursive
structure is based on repeatedly dividing the array into two parts.
QuickSort also keeps a small recursion depth because it recursively processes
only the smaller partition and handles the larger partition with a loop.
The duplicate-heavy input results in particularly small QuickSort and
QuickSelect depths because many elements can belong to the equality region.
QuickSelect may have a larger depth on random and sorted inputs because the
random pivot can lead to different partition sizes.


## 6. Ratio and Θ Check

To compare the measured number of comparisons with the expected asymptotic
growth, a ratio was calculated for each algorithm.

For MergeSort and QuickSort, the ratio is:

`comparisons / (n * log2(n))`

For QuickSelect, the ratio is:

`comparisons / n`

If the ratio becomes approximately constant as `n` increases, this supports
the expected Θ-bound.

For the largest input size, `n = 1,000,000`, the ratios were:

| Algorithm | Random | Sorted | Duplicates |
|---|---:|---:|---:|
| MergeSort | 0.998 | 0.455 | 0.950 |
| QuickSort | 1.243 | 1.317 | 0.166 |
| QuickSelect | 3.899 | 2.724 | 2.302 |

For MergeSort, the ratio stays close to a constant as the input size grows.
The values are approximately between 0.45 and 1.00 for the largest tested
sizes. This is consistent with the expected Θ(n log n) comparison growth.

QuickSort also shows a bounded ratio, but the constant depends more strongly
on the input type. For example, the duplicate-heavy input has a smaller
ratio because the three-way partition handles equal values together.

For QuickSelect, the ratio is calculated using `comparisons / n`. The values
remain within a limited range over the tested input sizes, although there is
more variation because the random pivot can produce different partition
sizes.

Using `n0 = 10,000`, rough empirical constants across the tested input types
can be chosen as follows:

| Algorithm | c1 | c2 | n0 |
|---|---:|---:|---:|
| MergeSort | 0.4 | 1.1 | 10,000 |
| QuickSort | 0.15 | 1.4 | 10,000 |
| QuickSelect | 1.9 | 4.0 | 10,000 |

These values are an empirical Θ check based on the measured data points.
They are not a mathematical proof for all possible values of `n`. Within
the tested range, the ratios remain bounded and do not show unbounded
growth, which is consistent with the expected asymptotic behavior.


## 7. Discussion

The experimental results are generally consistent with the expected
theoretical complexity of the algorithms. MergeSort shows relatively stable
comparison growth because its recursive structure and linear merge operation
are similar for different input types. QuickSort is more sensitive to the
input distribution because its performance depends on the partitions created
by the pivot. Random pivot selection reduces the chance of repeatedly getting
very unbalanced partitions, while the three-way partition makes QuickSort
especially effective for inputs with many duplicate values.

The measured execution times are not perfectly smooth because real systems
introduce additional factors. JVM warm-up can make the first executions
slower, so a warm-up run was used before collecting the measured results.
Garbage collection and memory allocation can also affect the running time of
large inputs. CPU cache and memory bandwidth can change the constant factors
even when two algorithms have the same asymptotic complexity. Finally, the
Insertion Sort cutoff in MergeSort reduces the overhead of recursion for
small subarrays, which can improve practical performance without changing
the overall Θ(n log n) complexity.

## 8. Plots

The following plots present the benchmark results for all three input types: random, sorted, and duplicate-heavy arrays.

### Time vs n

![Time vs n - Random](<plots/Time Vs N - Random.png>)

![Time vs n - Sorted](<plots/Time vs n — sorted.png>)

![Time vs n - Duplicates](<plots/Time vs n — duplicates.png>)

### Maximum Recursion Depth vs n

![Recursion depth vs n - Random](<plots/Recursion depth vs n — random.png>)

![Recursion depth vs n - Sorted](<plots/Recursion depth vs n — sorted.png>)

![Recursion depth vs n - Duplicates](<plots/Recursion depth vs n — duplicates.png>)

### Ratio vs n

![Ratio vs n - Random](<plots/Ratio vs n — random.png>)

![Ratio vs n - Sorted](<plots/Ratio vs n — sorted.png>)

![Ratio vs n - Duplicates](<plots/Ratio vs n — duplicates.png>)

The time plots show the measured execution time as the input size increases. The recursion-depth plots
show how the maximum recursion depth changes with `n`.
symptotic growth functions. The X-axis is displayed on a logarithmic scale because the benchmark uses input sizes from 1,000 to 1,000,000. recursion depth changes with `n`.

## 9. Conclusion

This assignment provided an opportunity to implement and evaluate three
Divide-and-Conquer algorithms in Java. MergeSort, QuickSort, and QuickSelect
were tested on different input sizes and input distributions. The
experimental results were broadly consistent with the expected theoretical
complexities, although the exact execution times were affected by practical
system factors.

The implementation also demonstrated the importance of algorithm design
choices. The reusable MergeSort buffer reduces unnecessary memory allocation,
while the Insertion Sort cutoff improves the handling of small subarrays.
QuickSort uses random pivot selection, three-way partitioning, and
smaller-side-first recursion to control its behavior and recursion depth.
QuickSelect reuses the same partitioning method and processes only the side
that can contain the required position. Overall, the benchmark and
correctness tests show how theoretical complexity and implementation details
both influence the practical performance of algorithms.