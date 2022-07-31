package collections.map.linkedHashMap;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.function.Function;

public class LinkedHashMapExample1 {

    /**
     * Create a constant PrintWriter since it will used many times ( in a lambda ), but only needs to be created once.
     */
    private final static PrintWriter printWriter = new PrintWriter(System.out);

    /**
     * My functon to increment an integer by 1
     */
    private static final Function<Integer, Integer> incrementBy1 = IncrementInteger.increment( IncrementInteger::increment, 1);


    /**
     * Increment a value by 1 for a key/value pair
     * 
     * Assumption : We will always have a valid key value pair
     * 
     * @param key           Part of a key value pair
     * @param value         Part of a key value pair
     * @return              The current value incremented by an Integer
     */
    public static Integer compute(Character key, Integer value ) {
        Integer nextValue = ( value == null ) ? 1 : incrementBy1.apply(value);
        return nextValue;
    }
    

    public static void main(String[] args) {

        String testString = " A A B C C B A D";

        /**
         * For each character (the key ) the value will be incremented or if the value is null will be set to 1
         */
        LinkedHashMap<Character, Integer> letterCounts = new LinkedHashMap<>();
        for ( Character character : testString.toCharArray() ) {
            //letterCounts.compute( character, (key, value) -> ( value == null ) ? 1 : value + 1);
            letterCounts.compute( character, LinkedHashMapExample1::compute);
        }

        System.out.println("---- Displaying values in the list");
        System.out.println("---- Observation 1 : Notice how the order is Kept where A B C and D in same relative order as in the String.");
        System.out.println("\tExample B comes before C and after A both in the String and Print out the key/values in the map\n"); 
        
        letterCounts.forEach( (key, value ) -> { printWriter.printf("Key = %s and value = %d\n", key, value); printWriter.flush(); });
            
        }

    }
