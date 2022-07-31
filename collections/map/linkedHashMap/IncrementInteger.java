package collections.map.linkedHashMap;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The purpose of this class is allow the developer to create function that will increment an integer.
 * 
 * This is an example of a partial function
 */
public class IncrementInteger {

    /**
     * A function that will set the value to a default value ( provided by the developer )
     * 
     * @param defaultValue  The value to use if the value is null
     * @param value         The value provided by the calling funciton
     * 
     * @return              0 if the value is null or the value itself.
     */
    public static Integer makeDefault(Integer defaultValue, Integer value) {
        Integer result = (defaultValue == null) ? defaultValue : value;
        return result;
    }

    /**
     * A fucntion that increment the value by a certian amount
     * 
     * @param valueToIncrementBy            The value to incresase the userValue by
     * @param userValue                     The value provide by the calling routine
     * @return                              valueToIncrement + userValue 
     */
    public static Integer increment(Integer valueToIncrementBy, Integer userValue) {
        Integer result = userValue + valueToIncrementBy;
        return result;
    }

    /**
     * Creates a function the user can use to increment value
     * 
     * From a function with the signature Integer BiFunction(Integer, Integer) a Integer Function(Integer) is created.
     * 
     * @param      func                     A function that with the Signature Integer BiFunction(Integer, Integer)
     * @param      valueToIncrementBy       The first of the two parameters that will be passed in
     * @return     A function with the Signature Integer Function(Integer)
     * 
     */
    public static Function<Integer, Integer> increment(
        BiFunction<Integer, Integer, Integer> func,
        Integer valueToIncrementBy ) {

            Function<Integer, Integer> partialFunction = (y) -> func.apply(valueToIncrementBy, y);           
            return partialFunction;   
    }

    public static void main (String[] args) {

        Integer testValue;

        testValue = 6;
        Integer result = IncrementInteger.makeDefault(0, testValue);
        result = IncrementInteger.increment(result, 10);
        
        System.out.println("The value of result is when calling the functions directly --> " + result);

        Function<Integer, Integer> incrementBy1Function =
            IncrementInteger.increment( IncrementInteger::increment, 10);

        testValue = 6;
        result = incrementBy1Function.apply(testValue);
        System.out.println("The value of the reuslt is when creating a partial application --> " + result);

        testValue = null;
    }
}