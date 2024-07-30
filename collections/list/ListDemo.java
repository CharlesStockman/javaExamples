package collections.list;

import java.util.Arrays;
import java.util.List;
import java.lang.UnsupportedOperationException;

// List interface methods: size, isEmpty, contains, iterator, toArray, toArray(T[] a, add, remove, containsAll, addAll, removeAll
// List interface methods: retainAll ( retains elements found in both list ), removeAll, replaceAll, sort, clear, equals, hashcode
// List interface methods: set(int index), get(int idnex, E elements ), add(index), remove(index), lastIndexOf, listIterator, subList(int start, int end)
// List interface methods: of, removeIf
//
// As of 2017 extends Collection
//
// Access in O(n)
public class ListDemo {

    public static void main(String args[]) {

        List<String> names = Arrays.asList( "John", "Anton", "Heinz" );
        System.out.println(names);
        names.set(1, "Anthony");
        System.out.println(names);

        try {
            names.add("Dirk");
        } catch ( UnsupportedOperationException exception) {
            System.out.println("Data Structure is " + names.getClass().getName());
            System.out.println("The asList function returns java.util.Arrys.ArrayList is backed by a fixed array so elemeents cannot be removed/added ");
        }

        // Actually stored in class with three primitive.  It implements the List2 and extends AbstractImmutableInterface
        // Cannot even change the value of the individual element
        List<String> names2 = List.of( "John", "Anton", "Heinz" );
        System.out.println(names2);
        try {
            names.add("Dirk");
        } catch ( UnsupportedOperationException exception) {
            System.out.println("Data Structure is " + names.getClass().getName());
            System.out.println("The asList function returns java.util.Arrys.ArrayList is backed by a fixed array so elemeents cannot be removed/added ");
        }

        // Review the Randome Access List
    }
}