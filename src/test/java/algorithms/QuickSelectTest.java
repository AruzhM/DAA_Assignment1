package algorithms;
import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuickSelectTest {
    @Test
    void testQuickSelect() {
        int[] array = {7, 2, 9, 1, 5, 3};

        Metrics metrics = new Metrics();

        int result = QuickSelect.select(array, 2, metrics);

        assertEquals(3, result);
    }

    @Test
    void testQuickSelectDuplicates() {
        int[] array = {5, 1, 5, 3, 2, 5};

        Metrics metrics = new Metrics();

        int result = QuickSelect.select(array, 3, metrics);

        assertEquals(5, result);
    }

    @Test
    void testInvalidK() {
        int[] array = {1, 2, 3};

        Metrics metrics = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, 3, metrics)
        );
    }

    @Test
    void testEmptyArray() {
        int[] array = {};

        Metrics metrics = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, 0, metrics)
        );
    }
}
