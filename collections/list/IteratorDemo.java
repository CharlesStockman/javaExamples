package collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

//
// Iterator
// ListIterator -- Bidirectional and elements can be modified
// SplitIterator -- An iterator defined for parallel processing and bulk operations
// Stream == Functinoal Iterator, but not an actual iterator

public class IteratorDemo {
    public static void main(String... args) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("John", "Anton", "Heinz", "John"));
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if ( name.contains("o") )
                System.out.println("Element = " + name);
            else
                iterator.remove();
        }

        System.out.println("names = " + names);
    }
}