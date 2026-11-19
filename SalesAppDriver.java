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

import java.util.Scanner;

public class SalesAppDriver {

	public static void main(String[] args) {
		
		 Scanner keyboard = new Scanner(System.in);

	        String inputName = promptForFileName(keyboard, "Enter input file name: ");
	        String outputName = promptForFileName(keyboard, "Enter output file name: ");

	        try {
	            double[][] data = SalesFileIO.readSalesData(inputName);

	            System.out.println();
	            System.out.println("Data loaded from " + inputName);
	            System.out.println();
	            System.out.print(SalesFileIO.buildSummary(data));

	            SalesFileIO.writeSummary(outputName, data);
	            System.out.println();
	            System.out.println("Summary written to " + outputName);

	        } 
	        catch (RuntimeException e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        keyboard.close();
		// To terminate the background running thread
		System.exit(0);
	}

	 private static String promptForFileName(Scanner keyboard, String message) {
	     System.out.print(message);
	     return keyboard.nextLine().trim();
	 }
}
