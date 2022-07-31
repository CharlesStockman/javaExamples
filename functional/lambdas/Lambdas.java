package functional.lambdas;

/**
 * Exercise 1
 * 
 * Basic lambdas. Make an array containing a few Strings. Sort it by
 * length (i.e., shortest to longest)
 *
 * reverse length (i.e., longest to shortest)
 *
 * alphabetically by the first character only
 */

/** 
 * Exercise 2
 * 
 * Making your own interfaces for which lambdas can be used. Your eventual goal is to make a
 * method called betterString that takes two Strings and a lambda that says whether the first of the two
 * is “better”. The method should return that better String; i.e., if the function given by the lambda
 * returns true, the betterString method should return the first String, otherwise betterString should
 * return the second String. Here are two examples of how your code should work when it is finished
 * (the first lambda example returns whichever of string1 and string2 is longer, and the second lambda
 * example always returns string1).
 * String string1 = ...;
 * String string2 = ...;
 * String longer = StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
 * String first = StringUtils.betterString(string1, string2, (s1, s2) -> true);
 * Accomplishing all of this requires you to do three things:
 * Define the TwoStringPredicate interface. It will specify a method that takes 2 strings and returns a
 * boolean. This interface is normal Java 7 code.
 * Define the static method betterString. That method will take 2 strings and an instance of your
 * interface. It returns string1 if the method in interface returns true, string2 otherwise. This method
 * is normal Java 7 code.
 * Call betterString. You can now use lambdas for the 3rd argument, as in the examples above.
 * package functional.lambdas;
 */

/** 
 * Exercise 3
 * 
 * Making generically-typed interfaces for which lambdas can be used. Use generics to replace
 * your String-specific solutions to problem 3 with generically typed solutions. That is, replace betterString with betterEntry and TwoStringPredicate with TwoElementPredicate. Make sure your previous examples still work when you only change betterString to betterElement. But, now you should
 * also be able to supply two Cars and a Car predicate, two Employees and an Employee predicate,
 * etc. For example:
 * ElementUtils.betterElement(string1, string2, (s1, s2) -> s1.length() > s2.length())
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.BiPredicate;
import java.lang.FunctionalInterface;

@FunctionalInterface
interface StringUtils {
    Boolean betterString(String s1, String s2, BiPredicate<String, String> predicate);
}

@FunctionalInterface 
interface ElementUtils<T> {
    Boolean betterElement(T e1, T e2, BiPredicate<T, T> predicate);
}
public class Lambdas<T> implements StringUtils, ElementUtils<T> {

    /**
     * A function to determine if the String is better.
     * 
     * @param s1    The first string
     * @param s2    The second string
     * 
     * @return (True) s1 is a better string than s2
     */
    public Boolean betterString(String s1, String s2 , BiPredicate<String, String> predicate) {
        Boolean result = predicate.test(s1,s2);
        return result;
    }

    /**
     * A function to determine if the Specific Object is better
     * 
     * @param e1    The first object
     * @param e2    The second object
     * 
     * @return (True)  e1 is a better string than e2 
     */
    public Boolean betterElement(T e1, T e2, BiPredicate<T,T> predicate) {
        Boolean result = predicate.test(e1, e2);
        return result;
    }

    /**
     * A function to call the lamba and print the results
     * 
     * data         The List of String being operated on
     * function     The function that will manipulate the data
     * prefix       The string print out before the data is.
     */
    public static void convenienceFunction(List<String> data, Consumer<List<String>> function, String prefix) {
        function.accept(data);
        System.out.println(prefix + data);
    }

    public static void main(String... args) {

        /*************************************************************/
        /* Exercise 1                                                */
        /*************************************************************/
        String[] names = new String[] { "Charles", "Barry ", "Alice Stockman", "Dennis", "Elanne" };
        List<String> nameList = Arrays.asList(names);
        System.out.println("The list of names are \t\t\t" + nameList);

        // Shortest to longest 
        Consumer<List<String>> shortestToLongest  = ( strList ) -> Collections.sort( strList, (s1, s2) -> s1.length() -  s2.length() );
        Lambdas.convenienceFunction(nameList, shortestToLongest, "The shortest to the longest are\t\t" );

        // Longest to shortest
        Consumer<List<String>> longestToShortest = ( strList ) -> Collections.sort( strList, (s1, s2) -> s2.length() - s1.length() );
        Lambdas.convenienceFunction(nameList, longestToShortest, "The longest to the shortest are\t\t" );

        // Alphabetically by first character only 
        Consumer<List<String>> sortByFirstCharacter = ( strList ) -> Collections.sort( strList, (s1, s2) -> s1.charAt(0) - s2.charAt(0) );
        Lambdas.convenienceFunction(nameList, sortByFirstCharacter, "The shortest to the longest are\t\t" );
        
        /*************************************************************/
        /* Exercise 2                                                */
        /*************************************************************/
        Lambdas<String> lambdas = new Lambdas<>();
        String s1 = "Chuck";
        String s2 = "Sharon";

        Boolean result = lambdas.betterString(s1, s2, (s3, s4) -> s3.length() > s4.length());
        System.out.printf("With input one as %s and input two as %s the function compares length and the result is %b\n", s1, s2, result);

        Boolean result2 = lambdas.betterString(s2, s1, (s3, s4) -> s3.length() > s4.length());
        System.out.printf("With input one as %s and input two as %s the function compares length and the result is %b\n", s2, s1, result2);

        Boolean result3 = lambdas.betterString(s1, s2, (s3, s4) -> true);
        System.out.printf("With input one as %s and input two as %s the function always returns true --> return value %b\n", s1, s2, result3);

        Boolean result4 = lambdas.betterString(s1, s2, (s3, s4) -> true);
        System.out.printf("With input one as %s and input two as %s the function always returns true --> return value %b\n", s2, s1, result4);

        /***************************************************************/
        /* Expercise 3                                                 */            
        /***************************************************************/
        Boolean result5 = lambdas.betterElement(s1, s2, (s3, s4) -> s3.length() > s4.length());
        System.out.printf("Using a generic function with parameter 1: %s and parameter 2: %s the return value should be false --> %b\n", s1, s2, result5);
    }

}