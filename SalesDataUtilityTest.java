
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalesDataUtilityTest {

	private static final double DELTA = 0.01;

    private double[][] data;

    @BeforeEach
    public void setUp() {
        data = new double[][] {
                { 100.50, 200.75, 150.00 },
                { 80.00, 90.00 },
                { 220.10, 130.50, 300.00, 50.25 }
        };
    }

    @Test
    public void testGetTotal() {
        assertEquals(1322.10, SalesDataUtility.getTotal(data), DELTA);
    }

    @Test
    public void testGetAverage() {
        assertEquals(146.90, SalesDataUtility.getAverage(data), DELTA);
    }

    @Test
    public void testRowAndColumnTotals() {
        assertEquals(451.25, SalesDataUtility.getRowTotal(data, 0), DELTA);
        assertEquals(170.00, SalesDataUtility.getRowTotal(data, 1), DELTA);
        assertEquals(700.85, SalesDataUtility.getRowTotal(data, 2), DELTA);

        assertEquals(400.60, SalesDataUtility.getColumnTotal(data, 0), DELTA);
        assertEquals(421.25, SalesDataUtility.getColumnTotal(data, 1), DELTA);
        assertEquals(450.00, SalesDataUtility.getColumnTotal(data, 2), DELTA);
        // Only the last row has a column 3
        assertEquals(50.25, SalesDataUtility.getColumnTotal(data, 3), DELTA);
    }

    @Test
    public void testHighestLowestInRow() {
        assertEquals(200.75, SalesDataUtility.getHighestInRow(data, 0), DELTA);
        assertEquals(100.50, SalesDataUtility.getLowestInRow(data, 0), DELTA);

        assertEquals(90.00, SalesDataUtility.getHighestInRow(data, 1), DELTA);
        assertEquals(80.00, SalesDataUtility.getLowestInRow(data, 1), DELTA);

        assertEquals(300.00, SalesDataUtility.getHighestInRow(data, 2), DELTA);
        assertEquals(50.25, SalesDataUtility.getLowestInRow(data, 2), DELTA);
    }

    @Test
    public void testHighestLowestInArray() {
        assertEquals(300.00, SalesDataUtility.getHighestInArray(data), DELTA);
        assertEquals(50.25, SalesDataUtility.getLowestInArray(data), DELTA);
    }

    @Test
    public void testInvalidIndex() {
        assertEquals(0, SalesDataUtility.getRowTotal(data, 10), DELTA);
        assertEquals(0, SalesDataUtility.getRowTotal(data, -1), DELTA);
        assertEquals(0, SalesDataUtility.getColumnTotal(data, 10), DELTA);
        assertEquals(0, SalesDataUtility.getHighestInRow(data, 10), DELTA);
        assertEquals(0, SalesDataUtility.getLowestInRow(data, -1), DELTA);
    }

    @Test
    public void testEmptyArray() {
        double[][] empty = new double[0][];
        assertEquals(0, SalesDataUtility.getTotal(empty), DELTA);
        assertEquals(0, SalesDataUtility.getAverage(empty), DELTA);
        assertEquals(0, SalesDataUtility.getHighestInArray(empty), DELTA);
        assertEquals(0, SalesDataUtility.getLowestInArray(empty), DELTA);
    }

}
