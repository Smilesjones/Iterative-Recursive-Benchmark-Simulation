/*
Filename: ManualWarmUpLoader.java
Author: Stephen Jones
Date: 15SEP2019
Purpose: Loader Class for the Dummy class used in warm-up.

Reference:

Baeldung. (2018, April 15). How to Warm Up the JVM. Retrieved from 
    https://www.baeldung.com/java-jvm-warmup

 */
package cmsc451project1;


public class ManualWarmUpLoader {
    protected static void load(){
        for(int i = 0; i < 1000000; i++){
            WarmUpDummyClass dummy = new WarmUpDummyClass();
            dummy.warmUpDummyMethod();
        }
    }
}
