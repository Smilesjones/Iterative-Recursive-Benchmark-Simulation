/*
Filename: BenchmarkSorts.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: Class that runs the benchmarking that saves and displays the result.
 */
package cmsc451project1;


public class BenchmarkSorts {
    
    //Variables
    //Variables for different sizes of data sets and number of repetitions
    private int[] sizes;
    private int testReps;
    //Keeps track of all data for that repetition for a particular size
    private int count = 0;
    //2-D arrays to store raw data from different repetitions
    private long [][] iterativeResultsCC, recursiveResultsCC;
    private long [][] iterativeResultsTime, recursiveResultsTime;
    //Arrays that store calculated data used in results
    private double [] aveItCounts, aveItTime, coVarItCounts, coVarItTime, 
            aveReCounts, aveReTime, coVarReCounts, coVarReTime;
    //private long [] aveItCounts;
    //Constructor
    public BenchmarkSorts(int []  sizes, int testReps){
        //Initialize different sizes and number of repititions
        this.sizes = sizes;
        this.testReps = testReps;
        
        //Initialize all arrays to number of sizes and total rep
        iterativeResultsCC = new long[sizes.length][testReps];
        recursiveResultsCC = new long[sizes.length][testReps];
        iterativeResultsTime = new long[sizes.length][testReps];
        recursiveResultsTime = new long[sizes.length][testReps];
        aveItCounts = new double[sizes.length];
        aveItTime = new double[sizes.length];
        coVarItCounts = new double[sizes.length];
        coVarItTime = new double[sizes.length];
        aveReCounts = new double[sizes.length];
        aveReTime = new double[sizes.length];
        coVarReCounts = new double[sizes.length];
        coVarReTime = new double[sizes.length];
        
    }
    
    //Method to generate randomly sorted arrays for a particular array length.
    private int [] generateTestArray(int arraySize){
        int [] testArray = new int[arraySize];
        //Produce array of integers from 0 to 9.
        for (int r = 0; r < arraySize; r++){
            //Options to produce best, worst, and average cases
            //testArray[r] = r;//best case
            //testArray[r] = arraySize - r;//worst case
            testArray[r] = (int)(Math.random()*10);//average case
        }
        return testArray;
    }
    
    //Method that runs the sorts benchmark
    public void runSorts(){
        //Outer Loop flow based on the number of different sizes desired
        for (int x = 0; x < sizes.length;x++){
            //Reset counter for raw and calculated data 
            count = 0;
            //Inner Loop flow based on number of test repetitions
            for (int i = 1; i <= testReps; i++){
                //Generate new test array for each repetition and use same array
                //for both iterative and recursive sorts
                int [] iterativeTestArray = generateTestArray(sizes[x]);
                int [] recursiveTestArray = iterativeTestArray.clone();
                //Iterative sorting and storage of raw data
                BubbleSort iterativeTestSort = new BubbleSort();
                iterativeTestSort.iterativeSort(iterativeTestArray);
                iterativeResultsCC [x][count] = iterativeTestSort.getCount();
                iterativeResultsTime [x][count] = iterativeTestSort.getTime();
                //Recursive sorting and storage of raw data 
                BubbleSort recursiveTestSort = new BubbleSort();
                recursiveTestSort.recursiveSort(recursiveTestArray);
                recursiveResultsCC [x][count] = recursiveTestSort.getCount();
                recursiveResultsTime [x][count] = recursiveTestSort.getTime();
                //Increment count of repetition
                count++;
            }
        }
    }
    //Method to calculate average time.
    private long calcAverageTime(long[] timeArray){
        long totalTime = 0;
        for (int i = 0; i < testReps; i++){
            totalTime += timeArray[i];
        }
        return totalTime/testReps;
    }
    //Method to calculate average counts.
    private long calcAverageCounts(long[] countArray){
        int totalCount = 0;
        for (int i = 0; i < testReps; i++){
            totalCount += countArray[i];
        }
        return totalCount/testReps;
    }
    //Method that displays the results of the runs
    public void displayResults(){
        //Call to calculate and store statistical data
        calcStats();
        
        //Variables for table headers
        String data = "Data Set";
        String size = "Size  ";
        String titleBlank = "";
        String iterative = "Iterative";
        String average = "Average";
        String critical = "Critical";
        String operation = "Operation";
        String countTitle = "Count";
        String coefficient = "Coefficient of";
        String variance = "Variance of";
        String execution = "Execution";
        String time = "Time";
        String recursive = "Recursive";
        String percent = "(%)";
        String headerFormat0 = "|%10s|%15s%44s|%15s\n";
        String headerFormat1 = 
                "|%10s|%13s|%15s|%13s|%15s|%13s|%15s|%13s|%15s|\n";
        String headerFormat2 = 
                "|%10s|%13s|%15s|%8s (ms)|%15s|%13s|%15s|%8s (ms)|%15s|\n"; 
        String headerFormat3 = 
                "|%10d|%13.0f|%15.2f|%13.2f|%15.2f|%13.0f|%15.2f|%13.2f|%15.2f|\n";
        
        drawTableSides();
        //Top Header
        System.out.format(headerFormat0, titleBlank, iterative ,titleBlank,
                recursive);
        
        drawTableSides();
        //Column Headers
        System.out.format(headerFormat1,  data, average, coefficient, average, 
                coefficient, average, coefficient, average, coefficient);
        System.out.format(headerFormat1, size, critical, variance, execution, 
                variance, critical, variance, execution, variance);
        System.out.format(headerFormat1, titleBlank, operation, countTitle, 
                time, time, operation, countTitle, time, time);
        System.out.format(headerFormat2, titleBlank, countTitle, percent, titleBlank, 
                percent, countTitle, percent, titleBlank, percent);
        
        drawTableSides();
        //Data
        for (int i = 0; i < sizes.length;i++){
            System.out.format(headerFormat3, sizes[i], aveItCounts[i], 
                    coVarItCounts[i], aveItTime[i], coVarItTime[i], 
                    aveReCounts[i], coVarReCounts[i], aveReTime[i], 
                    coVarReTime[i]);
        }
        
        drawTableSides();
    }
    //Method for sides of table
    private void drawTableSides(){
        for(int i = 0; i < 132; i++){
            if(i == 0 || i == 131){
                System.out.print("|");
                continue;
            }
            System.out.print("-");
        }
        System.out.println("");
    }
    //Method to calculate and store desired statistics
    private void calcStats(){
        long aveCounts;
        long aveTime;
        //Iterative Calculations
        //Loop to calculate/store average and coefficient of variances 
        for (int i = 0; i < sizes.length; i++){
            //Iterative Counts
            aveCounts = calcAverageCounts(iterativeResultsCC[i]);
            aveItCounts [i] = aveCounts;
            coVarItCounts [i] = calcCV(calcSD(iterativeResultsCC[i]), aveCounts);
            //Iterative Time
            aveTime = calcAverageTime(iterativeResultsTime[i]);
            //Store average time as milliseconds
            aveItTime [i] = aveTime/1000000.00;
            coVarItTime[i] = calcCV(calcSD(iterativeResultsTime[i]), aveTime);
            //Recursive Counts
            aveCounts = calcAverageCounts(recursiveResultsCC[i]);
            aveReCounts [i] = aveCounts;
            coVarReCounts [i] = calcCV(calcSD(recursiveResultsCC[i]), aveCounts);
            //Recursive Time
            aveTime = calcAverageTime(recursiveResultsTime[i]);
            //Store average time as milliseconds
            aveReTime [i] = aveTime/1000000.00;
            coVarReTime[i] = calcCV(calcSD(recursiveResultsTime[i]), aveTime);
        }
    }
    //Method to Calculate the Coefficient of Variation
    private double calcCV(double standardDeviation, long average){
        return (standardDeviation/average)*100;
    }
    //Method to Calculate the Standard Deviation
    private double calcSD(long [] dataSet){
        //Variables for different stages of calculation
        long totalValue = 0;
        long totalValueSquared = 0;
        double tempVariableOne = 0;
        double tempVariableTwo = 0;
        double tempVariableThree = 0;
        double tempVariableFour = 0;
        double sD = 0;
        //Sum the total and total of squares
        for (int i = 0; i < dataSet.length; i++){
            totalValue += dataSet[i];
            totalValueSquared += Math.pow(dataSet[i],2);
        }
        //Square the total
        tempVariableFour = Math.pow(totalValue, 2);
        //Divide by repetitions
        tempVariableOne = tempVariableFour/testReps;
        //Subtract from total of squares
        tempVariableTwo = totalValueSquared - tempVariableOne;
        //Divide by repetitions minus 1
        tempVariableThree = tempVariableTwo/(testReps - 1);
        //Take the square root
        sD = Math.sqrt(tempVariableThree);
        
        return sD;
    }
}
