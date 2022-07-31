/**
 * For these exercises, start with a List of Strings similar to this: List<String> words = Arrays.asList("hi", "hello", ...);
 *
 * Produce a single String that is the result of concatenating the uppercase versions of all of the
 *  Strings. E.g., the result should be "HIHELLO...". Use a single reduce operation, without using map.
 * 
 * Produce the same String as above, but this time via a map operation that turns the words into upper
 * case, followed by a reduce operation that concatenates them.
 *
 * Produce a String that is all the words concatenated together, but with commas in between. E.g., the
 * result should be "hi,hello,...". Note that there is no comma at the beginning, before “hi”, and also no
 * comma at the end, after the last word. Major hint: there are two versions of reduce discussed in the
 * notes.
 * 
 * Find the total number of characters (i.e., sum of the lengths) of the strings in the List.
 *
 * Find the number of words that contain an “h”.
 */

import java.util.List;
import java.util.Arrays;

public class Stream2 {

    public static Integer provideNumberOfCharactersInArrayOFStrings(List<String> strings) {
        int sum = 0;
        
        for ( String string : strings ) {
            sum += string.length();
        }

        return sum;
    }

    public static void main(String... args) {

        List<String> words = Arrays.asList("hi", "hello", "aloh", "bye", "goodbye", "aloh");

        // Produce a single String that is the result of concatenating the uppercase versions of all of the
        // Strings. E.g., the result should be "HIHELLO...". Use a single reduce operation, without using map.
        String answer = words.stream().reduce("", (x,y) -> x + y.toUpperCase());
        System.out.println("The answer is " + answer);
        System.out.println("++++++++++++++++++++++++");


        // Produce the same String as above, but this time via a map operation that turns the words into upper
        // case, followed by a reduce operation that concatenates them.
        String answer2 = words.stream().map(String::toUpperCase).reduce("", (x,y) -> x + y);
        System.out.println("The second answer is " + answer2);
        System.out.println("++++++++++++++++++++++++");


        // Produce a String that is all the words concatenated together, but with commas in between. E.g., the
        // result should be "hi,hello,...". Note that there is no comma at the beginning, before “hi”, and also no
        // comma at the end, after the last word. Major hint: there are two versions of reduce discussed in the
        // notes.
        String answer3 = words.stream().reduce((x,y) -> x + "," + y).orElse("There must be at least two paraemters");
        System.out.println("The third answer is " + answer3);

        String answer3a = words.stream().reduce("", (x,y) -> x + "," + y);
        System.out.println("Note by adding an intial empty space the first concat is \"\" + , + word1 --> " + answer3a);
        System.out.println("++++++++++++++++++++++++");

        // Find the total number of characters (i.e., sum of the lengths) of the strings in the List.
        int result = Stream2.provideNumberOfCharactersInArrayOFStrings(words);
        System.out.println("First Step get the number of characters in the string to verify the solution is correct --> " + result);

        int answer4 = words.stream().map( (x) -> x.length()).reduce(0, Integer::sum);
        System.out.println("The fourth answer is " + answer4);

        int answer4a = words.stream().mapToInt( (x) -> x.length()).sum();
        System.out.println("Another version of the fourth answer is " + answer4a);

        System.out.println("Sanity Check is result and the answer the same --> " + (result == answer4) ); 
        System.out.println("++++++++++++++++++++++++");

        //  Find the number of words that contain an “h”.
        long answer5 = words.stream().filter( (s) -> s.contains("h")).count();
        System.out.println("The fifth answer is " + answer5);

        


        
    



    }
}