/*
Filename: BubbleSort.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: Class that implements the recursive and iterative bubble sort, includes
methods to return counts and time for sort.
 */
package cmsc451project1;

import javax.swing.JOptionPane;


public class BubbleSort implements SortInterface {

    //Variables
    private boolean wasSwapped;
    private int [] list;
    private int swapCount;
    private long startTime, endTime, duration;
    
    
    //Method that runs the recursive version of the sort
    @Override
    public void recursiveSort(int[] list) {
        //Assign local variable to instance variable
        this.list = list;
        
        //Track time only from intial recursion activation
        startTime = System.nanoTime();
        bubbleSortRec(list.length-1);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        //Check that array is sorted
        confirmSort();
    }
    
    //BubbleSort Recusive Method
    private void bubbleSortRec(int n){
        //Base Case: no more elements to compare
        if(n == 0){
            return;
        }
        //Reset.  If no swap, the array is sorted, so no more recursion 
        wasSwapped = false;
        
        //Recursive method for a single pass through the array
        bubbleLargestRecursive(0, n);
        
        if(wasSwapped){//test if swap happened
            //If a swap was made, recurse minus the last element
            bubbleSortRec(n-1);
        }
        
    }
    
    //Recursive method to traverse through the array
    private void bubbleLargestRecursive(int i, int n){
        //Base Case: at end of array
        if(i == n){
            return;
        }
        //Test if current and next elements out of order
        if (list[i] > list[i+1]){
            swapElements(i,i+1);
            wasSwapped = true;
        }
        //Recurse to next element
        bubbleLargestRecursive(i+1 , n);
    }

    //Method that runs the iterative version of the sort
    @Override
    public void iterativeSort(int[] list) {
        
        this.list = list;
        int n = list.length;
        
        startTime = System.nanoTime();
        if(n == 1|n == 0){
            return;
        }
        //Loop
        do {
            //Reset, if no swaps made, array is sorted, so no more iterations
            wasSwapped = false;
            //Traverse through the array
            for (int i = 0; i < n - 1; i++){
                //Test if current element and next element are out of order
                if(list[i] > list[i+1]){
                    swapElements(i, i+1);
                    wasSwapped = true; 
                }
            }
            //Loop again minus the last element
            n--;
        }while(wasSwapped);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        //Check that array is sorted
        confirmSort();
    }
    
    //Method that swaps elements
    private void swapElements(int indexOne, int indexTwo){
        //Critical Operation Count
        swapCount++;
        //Swap Elements with temp variable
        int tempValue = list[indexOne];
        list[indexOne] = list[indexTwo];
        list[indexTwo] = tempValue;
    }
    
    //Method that returns the array list
    public int[] getList(){
        return list;
    }

    //Method that returns the critical operation count
    @Override
    public int getCount() {
        return swapCount;
    }

    //Method that returns the duration of the sort
    @Override
    public long getTime() {
        return duration;
    }
    //Method to confirm array is sorted.  Throws an exception
    private void confirmSort(){
        //System.out.println("In confirmSort()");
        try {
        for(int i = 0; i < list.length-1; i++){
            if(list[i] > list[i+1]){
                    throw new UnsortedException();
                } 
            }
        }catch (UnsortedException ex) {
                 JOptionPane.showMessageDialog(null, "Array is not Sorted", 
                         "Sorting Error", JOptionPane.ERROR_MESSAGE);
                }
    }
    
}
