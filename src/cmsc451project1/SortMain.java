/*
Filename: SortMain.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: The main method to initiate sorting benchmark program

 */
package cmsc451project1;


public class SortMain {

    
    public static void main(String[] args) {
        //Loop to provide warm-up 
        for(int w = 0; w < 5; w++){
            long start = System.nanoTime();
            ManualWarmUpLoader.load();
            long end = System.nanoTime();
            System.out.println("Warm-up time taken : " + (end - start));
        }
        
        //Number of repetitions of sorting per array size 
        int testReps = 50 ;
        
        //Different sizes of unsorted arrays
        int[]sizesMini = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[]sizesSmall = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        int[]sizes = {100, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000};
        int[]sizesBig = {5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000};
        
        //Create Benchmark object and running of sorting and results methods
        BenchmarkSorts bubbleSort = new BenchmarkSorts(sizesSmall , testReps);
        bubbleSort.runSorts();
        bubbleSort.displayResults();
    }
    
}
