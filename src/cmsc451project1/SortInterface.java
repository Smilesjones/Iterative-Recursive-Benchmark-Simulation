/*
Filename: SortInterface.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: Interface for the sorting algorithms.

Note:
Interface specificications from CMSC 451 Project 1
 */
package cmsc451project1;


public interface SortInterface {
    
    public void recursiveSort(int [] list);
    public void iterativeSort(int [] list);
    public int getCount();
    public long getTime();
    
}
