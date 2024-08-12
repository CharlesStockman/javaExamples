package collections.maps;

import java.util.HashSet;

/**
 * Give the illusion that items are stored, but this an illusion (ex. Inserting integers in natural order
 *
 * A good hashcode is hard to write need to try it out for good performance
 *
 * After Java 8 -- Write a class which has an hashcode that clashes a lot then use Comparable Interface so that
 * a binary tree can be constructed for the clashes
 *
 * @see { SetDemo.java }
 */
public class HashSetDemo {
    public static void main(String... args ) {
        HashSet<String> orderSet = new HashSet<>();
        orderSet.add("1");
        orderSet.add("2");
        orderSet.add("3");

        System.out.println("Looks like it is sorted -- " + orderSet);

        orderSet.add("11");
        orderSet.add("13");

        System.out.println("Now it does not look ordered " + orderSet);
    }
}
