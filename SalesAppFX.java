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
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalesAppFX extends Application {
	private TextField inputField;
    private TextField outputField;
    private TextArea outputArea;
    private Button loadButton;
    private Button saveButton;

    // Holds the loaded data between button clicks
    private double[][] data;

    @Override
    public void start(Stage stage) {
        inputField = new TextField("salesdata.txt");
        outputField = new TextField("sales_summary.txt");

        GridPane fields = new GridPane();
        fields.setHgap(10);
        fields.setVgap(10);
        fields.add(new Label("Input File:"), 0, 0);
        fields.add(inputField, 1, 0);
        fields.add(new Label("Output File:"), 0, 1);
        fields.add(outputField, 1, 1);

        loadButton = new Button("Load & Process");
        saveButton = new Button("Save Summary");
        loadButton.setOnAction(e -> loadAndProcess());
        saveButton.setOnAction(e -> saveSummary());
        HBox buttons = new HBox(10, loadButton, saveButton);

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(12);

        VBox root = new VBox(12, fields, buttons, outputArea);
        root.setPadding(new Insets(12));

        stage.setTitle("Store Sales Data Processor");
        stage.setScene(new Scene(root, 620, 450));
        stage.show();
    }

    private void loadAndProcess() {
        try {
            String filename = inputField.getText().trim();
            data = SalesFileIO.readSalesData(filename);
            outputArea.setText("Data loaded from " + filename + "\n\n" + SalesFileIO.buildSummary(data));
        } catch (RuntimeException e) {
            data = null;
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void saveSummary() {
        if (data == null) {
            outputArea.setText("Please load a file first (click Load & Process).");
            return;
        }
        try {
            String filename = outputField.getText().trim();
            SalesFileIO.writeSummary(filename, data);
            outputArea.appendText("\nSummary saved to " + filename + "\n");
        } catch (RuntimeException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
