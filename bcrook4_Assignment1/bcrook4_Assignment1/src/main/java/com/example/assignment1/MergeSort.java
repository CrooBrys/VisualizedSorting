//Package
package com.example.assignment1;

//Importing classes
import javafx.application.Platform;

//Creating class
public class MergeSort extends SortingHubController implements SortingStrategy{

    //Fields
    private int[] list;
    private SortingHubController controller;

    //Constructor
    MergeSort(int[] list, SortingHubController object){

        //Assigning fields
        this.list = list;
        this.controller = object;
    }

    //Helper method to divide and merge arrays for mergesort
    void merge(int[] array, int start, int middle, int end)
    {

        //Declaring & initializing variables
        int newStart = middle + 1;

        //Checking if already sorted
        if (array[middle] <= array[newStart]) {
            return;
        }

        //Iterating through array
        while (start <= middle && newStart <= end) {

            //Checking if first element is in correct position
            if (array[start] <= array[newStart]) {

                //Incrementing
                start++;
            }

            //If first element is not in correct position
            else {

                //Declaring & initializing variables
                int value = array[newStart];
                int index = newStart;

                //Shifting elements
                while (index != start) {
                    array[index] = array[index - 1];
                    index--;

                    //Try-catch block
                    try{

                        //Delaying graph update
                        Platform.runLater(()->controller.updateGraph(list));
                        Thread.sleep(2);
                    }
                    catch(Exception ex){
                    }
                }

                //Assigning value to index
                array[start] = value;

                //Try-catch block
                try{

                    //Delaying graph update
                    Platform.runLater(()->controller.updateGraph(list));
                    Thread.sleep(2);
                }
                catch(Exception ex){
                }

                //Incrementing
                start++;
                middle++;
                newStart++;
            }
        }
    }

    //Helper method to complete mergeSort
    public void mergeSort(int[] array, int leftIndex, int rightIndex){

        //Checking if leftIndex is smaller than rightIndex
        if (leftIndex < rightIndex) {

            //Declaring & initializing variable
            int m = leftIndex + (rightIndex - leftIndex) / 2;

            //Recursive call
            mergeSort(array, leftIndex, m);
            mergeSort(array, m + 1, rightIndex);

            //Calling helper method
            merge(array, leftIndex, m, rightIndex);
        }
    }

    //Implementing sort method
    @Override
    public void sort(int[] array) {

        //Calling mergeSort method
        mergeSort(array, 0, array.length - 1);
    }

    //Implementing run method
    @Override
    public void run() {

        //Calling sort method
        sort(list);
    }
}