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

public class SalesDataUtility {
	
	// Utility class with no instance methods needed
	private SalesDataUtility() {
	}
	
	// Get the total of every value in the array
	public static double getTotal(double[][] data) {
        double total = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                total += data[row][col];
            }
        }
        return total;
    }
	// Average of every value in the array (0 if there are no values)
	 public static double getAverage(double[][] data) {
		 int count = 0;
		 for (int rows = 0; rows < data.length; rows++) {
			 for (int cols = 0; cols < data[rows].length; cols++) {
				 count++;
	         }
	     }
	     if (count == 0) {
	    	 return 0;
	     }
	     return getTotal(data) / count;
	  }
	 
	 /** Sum of one row (0 if the row index is invalid). */
	   public static double getRowTotal(double[][] data, int row) {
	        if (row < 0 || row >= data.length) {
	            return 0;
	        }
	        double total = 0;
	        for (int c = 0; c < data[row].length; c++) {
	            total += data[row][c];
	        }
	        return total;
	    }

	    /**
	     * Sum of one column. Rows that are too short to have this column
	     * are skipped (0 if the column index is invalid or negative).
	     */
	    public static double getColumnTotal(double[][] data, int col) {
	        if (col < 0) {
	            return 0;
	        }
	        double total = 0;
	        for (int r = 0; r < data.length; r++) {
	            if (col < data[r].length) {
	                total += data[r][col];
	            }
	        }
	        return total;
	    }

	    /** Highest value in one row (0 if the row is invalid or empty). */
	    public static double getHighestInRow(double[][] data, int row) {
	        if (row < 0 || row >= data.length || data[row].length == 0) {
	            return 0;
	        }
	        double highest = data[row][0];
	        for (int c = 1; c < data[row].length; c++) {
	            if (data[row][c] > highest) {
	                highest = data[row][c];
	            }
	        }
	        return highest;
	    }

	    /** Lowest value in one row (0 if the row is invalid or empty). */
	    public static double getLowestInRow(double[][] data, int row) {
	        if (row < 0 || row >= data.length || data[row].length == 0) {
	            return 0;
	        }
	        double lowest = data[row][0];
	        for (int c = 1; c < data[row].length; c++) {
	            if (data[row][c] < lowest) {
	                lowest = data[row][c];
	            }
	        }
	        return lowest;
	    }

	    /** Highest value anywhere in the array (0 if there are no values). */
	    public static double getHighestInArray(double[][] data) {
	        boolean found = false;
	        double highest = 0;
	        for (int r = 0; r < data.length; r++) {
	            for (int c = 0; c < data[r].length; c++) {
	                if (!found || data[r][c] > highest) {
	                    highest = data[r][c];
	                    found = true;
	                }
	            }
	        }
	        return highest;
	    }

	    /** Lowest value anywhere in the array (0 if there are no values). */
	    public static double getLowestInArray(double[][] data) {
	        boolean found = false;
	        double lowest = 0;
	        for (int r = 0; r < data.length; r++) {
	            for (int c = 0; c < data[r].length; c++) {
	                if (!found || data[r][c] < lowest) {
	                    lowest = data[r][c];
	                    found = true;
	                }
	            }
	        }
	        return lowest;
	    }
}
