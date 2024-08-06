package collections.sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * See CopyOnWriteSet is the same as CopyOnWriteList except there are no duplication
 *
 * Sets don't care about duplications, but equality
 * With a TreeSet identity is dependent on the CompareTo
 *
 * Good use is Observable 
 *
 * Inserting is Quadratic Performance ( double the size it becomes four times as slow )
 */
public class CopyOnWriteArraySetDemo {
    public static void main(String... args) {
        Set<String> names = new CopyOnWriteArraySet<>(Arrays.asList("John", "Anton", "Heinz", "John"));
        names.forEach( ( name -> {
            System.out.println(name);
            if (name.contains("o")) names.remove(name); }
        ));
        System.out.println(names);
    }
}