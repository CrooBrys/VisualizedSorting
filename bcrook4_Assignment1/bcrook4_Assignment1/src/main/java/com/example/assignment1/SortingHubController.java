//Package
package com.example.assignment1;

//Importing Classes
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

//Creating main class
public class SortingHubController implements Initializable {

    //Assigning attributes
    private int[] intArray;
    private SortingStrategy sortingMethod;
    @FXML
    private ComboBox<String> algorithmComboBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label arraySizeLabel;
    @FXML
    private Slider arraySizeSlider;

    //Creating slider listener
    @FXML
    void sliderListener(MouseEvent event) {

        //Setting arraySizeLabel to correct value
        arraySizeLabel.setText(String.format("%d", (int)arraySizeSlider.getValue()));

        //Randomizing array
        initializeArray();

        //Updating graph
        updateGraph(intArray);
    }

    //Creating resetButton listener
    @FXML
    void resetButtonListener(ActionEvent event) {

        //Setting arraySizeSlider
        arraySizeSlider.setValue(64);

        //Setting arraySizeLabel
        arraySizeLabel.setText(String.format("%.0f", arraySizeSlider.getValue()));

        //Randomizing array
        initializeArray();

        //Updating graph
        updateGraph(intArray);
    }

    //Creating sortButtonListener
    @FXML
    void sortButtonListener(ActionEvent event) {

        //Setting sorting method
        setSortStrategy();

        //Creating new Thread Object
        Thread myThread = new Thread(sortingMethod);

        //Starting thread
        myThread.start();
    }

    //Method to set sorting strategy
    @FXML
    void setSortStrategy(){

        //If merge sort is selected
        if(algorithmComboBox.getSelectionModel().getSelectedItem() == "Merge Sort"){

            //Setting sortingMethod to MergeSort object
            sortingMethod = new MergeSort(intArray, this);
        }

        //If selection sort is selected
        else{

            //Setting sortingMethod to SelectionSort object
            sortingMethod = new SelectionSort(intArray, this);
        }
    }

    //Creating initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Adding items to algorithmComboBox
        algorithmComboBox.getItems().addAll("Merge Sort", "Selection Sort");

        //Adding default selection to comboBox
        algorithmComboBox.setValue("Merge Sort");

        //Setting default arraySizeLabel value
        arraySizeLabel.setText(String.valueOf((int)arraySizeSlider.getValue()));

        //Randomizing array
        initializeArray();

        //Displaying rectangles
        rectangle(intArray);
    }

    //Method to update graph
    void updateGraph(int[] data){

        //Clearing rectangles
        pane.getChildren().clear();

        //Adding rectangles
        rectangle(data);
    }

    //Method to initialize array
    public void initializeArray(){

        //Initializing array length
        intArray = new int[(int)arraySizeSlider.getValue()];

        //Declaring/initializing variable
        int count = 0;

        //Looping through array
        while(count < intArray.length){

            //Declaring/initializing variable
            boolean duplicate = false;

            //Generating random number
            int randomNum = (int)(Math.random() * (intArray.length - 1 + 1) + 1);

            //Checking if random number already exists
            for(int i = 0; i < intArray.length; i++){
                if(randomNum == intArray[i]){

                    //Setting duplicate to true
                    duplicate = true;
                }
            }

            //Checking if duplicate is true
            if(!duplicate){

                //Assigning value to index
                intArray[count] = randomNum;

                //Incrementing
                count++;
            }
        }
    }

    //Method to create rectangles
    public void rectangle(int[] data){

        //Creating height constant
        double heightConst = pane.getMaxHeight() / data.length;

        //Rectangle starting value
        double parentX = 1;

        //Rectangle width
        double recWidth = (pane.getMaxWidth() - 1) / data.length - 1;

        //Iterating through array
        for(int i = 0;i < data.length;i++){

            //Setting rectangle height
            double recHeight = data[i] * heightConst - 2;

            //Creating rectangle object
            Rectangle rec = new Rectangle(parentX, pane.getMaxHeight() - recHeight - 1, recWidth, recHeight);

            //Setting colour of rectangle
            rec.setFill(Color.RED);

            //Adding rectangle as child of pane
            pane.getChildren().add(rec);

            //Re-assigning parentX
            parentX = parentX + recWidth + 1;
        }
    }
}