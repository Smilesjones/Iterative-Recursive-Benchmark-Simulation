/*
Filename: UnsortedException.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: Exception class that is thrown when unsorted array is detected.
 */
package cmsc451project1;


public class UnsortedException extends Exception {

    
    public UnsortedException() {
    }

    public UnsortedException(String msg) {
        super(msg);
    }
}
