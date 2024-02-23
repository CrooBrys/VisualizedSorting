//Package
package com.example.assignment1;

//Importing classes
import javafx.application.Platform;

//Creating class
public class SelectionSort extends SortingHubController implements SortingStrategy{

    //Fields
    private int[] list;
    private SortingHubController controller;

    //Constructor
    SelectionSort(int[] list, SortingHubController object){

        //Assigning fields
        this.list = list;
        this.controller = object;
    }

    //Implementing sorting method
    @Override
    public void sort(int[] numbers) {

        //Declaring & initialing variable
        int x = numbers.length;

        //Iterating through array
        for (int i = 0; i < x-1; i++)
        {
            //Finding max element of array
            int minimum = i;
            for (int j = i+1; j < x; j++)
                if (numbers[j] < numbers[minimum])
                    minimum = j;

            //Swapping minimum element with first
            int temp = numbers[minimum];
            numbers[minimum] = numbers[i];
            numbers[i] = temp;

            //Try-catch block
            try{

                //Delaying graph update
                Platform.runLater(()->controller.updateGraph(list));
                Thread.sleep(60);
            }
            catch(Exception ex){
            }
        }
    }

    //Implementing run method
    @Override
    public void run() {

        //Calling sorting method
        sort(list);
    }
}