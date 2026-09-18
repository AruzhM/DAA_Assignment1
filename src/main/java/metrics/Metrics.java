package metrics;

public class Metrics {
    private long comparisons;
    private int currentDepth;
    private int maxDepth;
    private long startTime;
    private long timeNanos;

    public void reset() {
        comparisons =0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        timeNanos = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        timeNanos = System.nanoTime() - startTime;
    }

    public void incrementComparisons() {
        comparisons++;
    }
    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    public double getTimeMillis() {
        return timeNanos / 1_000_000.0;
    }
}

