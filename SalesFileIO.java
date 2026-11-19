/*
 * Class: CMSC-203-20798 Computer Science I
 * Instructor: Dr. Ahmed Tarek
 * Description: Write a Java program that processes sales data for multiple departments over multiple days. 
 * The sales data is a ragged 2D array (each row may have a different number of columns) of double values. 
 * You will read sales data from a text file into a 2D ragged array. Use a set of static utility methods to compute row/column totals, highest/lowest values, and overall totals. Write summary results to an output file. 
 * Test your utility methods using JUnit. Use a JavaFX GUI to select an input file, process it, and display results. 
 * This assignment emphasizes arrays-of-arrays (ragged 2D arrays), static methods, file input/output, and unit testing, consistent with mid-course Java topics. 
 * Due: 11/18/26
 * Platform/compiler: Eclipse IDE
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Nebiyou Daniel
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SalesFileIO {
	
	private SalesFileIO() {
    }

    /**
     * Reads a file into a ragged 2D array.
     *
     * @throws UncheckedIOException     if the file cannot be read
     * @throws IllegalArgumentException if a token is not a valid number
     */
    public static double[][] readSalesData(String filename) {
        List<double[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }
                String[] tokens = line.split("\\s+");
                double[] row = new double[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    try {
                        row[i] = Double.parseDouble(tokens[i]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "Invalid number '" + tokens[i] + "' on line " + lineNumber);
                    }
                }
                rows.add(row);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read file: " + filename, e);
        }

        // Build the ragged array: each row is allocated at its own length
        double[][] data = new double[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }

    /**
     * Builds the summary report text (shared by the file writer,
     * the console driver, and the GUI).
     */
    public static String buildSummary(double[][] data) {
        StringBuilder sb = new StringBuilder();

        sb.append(fmt("Total sales: ", SalesDataUtility.getTotal(data)));
        sb.append(fmt("Average sale: ", SalesDataUtility.getAverage(data)));
        sb.append(fmt("Highest sale: ", SalesDataUtility.getHighestInArray(data)));
        sb.append(fmt("Lowest sale: ", SalesDataUtility.getLowestInArray(data)));
        sb.append("\n");

        for (int r = 0; r < data.length; r++) {
            sb.append(fmt("Row " + r + " total: ", SalesDataUtility.getRowTotal(data, r)));
        }
        sb.append("\n");

        int maxCols = maxColumns(data);
        for (int c = 0; c < maxCols; c++) {
            sb.append(fmt("Column " + c + " total: ", SalesDataUtility.getColumnTotal(data, c)));
        }
        sb.append("\n");

        for (int r = 0; r < data.length; r++) {
            sb.append(fmt("Row " + r + " highest: ", SalesDataUtility.getHighestInRow(data, r)));
            sb.append(fmt("Row " + r + " lowest: ", SalesDataUtility.getLowestInRow(data, r)));
        }
        return sb.toString();
    }

    /**
     * Writes the summary report to a file.
     *
     * @throws UncheckedIOException if the file cannot be written
     */
    public static void writeSummary(String filename, double[][] data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(buildSummary(data));
        } catch (IOException e) {
            throw new UncheckedIOException("Could not write file: " + filename, e);
        }
    }

    // ---- private helpers ----

    private static int maxColumns(double[][] data) {
        int max = 0;
        for (int r = 0; r < data.length; r++) {
            if (data[r].length > max) {
                max = data[r].length;
            }
        }
        return max;
    }

    private static String fmt(String label, double value) {
        return label + String.format(Locale.US, "%.2f", value) + "\n";
    }

}
