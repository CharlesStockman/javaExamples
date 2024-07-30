package collections.sets;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

/**
 * A TreeSet is sorted.
 */
public class TreeSetDemo {

    public static void main(String args[]) {

            // Will return an Error from javac since ArrayList is not comparable.
            // ArrayList<String> data = new ArrayList<>();
            // data.add("Chuck");
            // Set<ArrayList<String>> test = new TreeSet<>(data);

            Set<Integer> oddsBetweenMinusTenAndTen = new TreeSet<>();
            oddsBetweenMinusTenAndTen.addAll(Arrays.asList(-9, -7, -5, 5, -1 , -3, 1, 1, 1, 9, 7, 3));
            System.out.println("Odds Between Minuse Ten and Ten" + oddsBetweenMinusTenAndTen);



    }
}