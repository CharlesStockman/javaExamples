import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Stream3 {

    public static void main(String... args) {

        // Write a Java program to convet the average of a list of integers using streams
        System.out.println("The average is " + IntStream.of(1,2,3).average().orElse(-1.0));

        // Write a program to convert a list of string to uppercase or lowercase uisng streams
        Stream.of("java", "python", "node").map(String::toUpperCase).forEach( s -> System.out.println(s));

        // Write a program to add to a list the sum of the even numbers and odd numbers
        System.out.println("The sums are: " + Stream.of(1,2,3,4,5,6,7,8,9).collect(
                Collectors.groupingBy( n -> n % 2 == 0 ? "even" : "odd", Collectors.summingInt(Integer::intValue))).
                values().stream().
                mapToInt(Integer::intValue).toArray());

        //System.out.println("The sums are: " + Stream.of(1,2,3,4,5,6,7,8,9).collect(
        //        Collectors.groupingBy( n -> n % 2 == 0 ? "even" : "odd", Collectors.summingInt(Integer::intValue))).getClass().getName());

        // Write a java program to remove all duplicates from a list
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1,1,1,2,2,3,4,5));
        System.out.println("A list without duplicates" + numbers.stream().distinct().toList());

        // Write a program to coutnt he number of strings  in a list that starts with a specific Letter
        ArrayList<String> strings = new ArrayList<>(List.of("ABC", "BAC", "CAB", "CBA", "BCA"));
        Predicate<String> firstletterIsC = s -> s.startsWith("C");
        System.out.println("The count of the first letter starting with C -- " + strings.stream().filter(firstletterIsC).count());


    }
}