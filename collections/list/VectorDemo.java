package collections.list;

import java.util.Arrays;
import java.util.Vector;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

// A thread safe class , but is quite slow unless you need synchronization
// Serialize it -- Quite expensive and grows more quickly.  When size is exceeded double the size of the new array
class VectorDemo {
    public static void main(String... args) {
        Vector<String> names = new Vector<>(Arrays.asList("John", "Anton", "Heinz"));

        // If you need synchronization 00 use synchronziationList instead
        // A vector is always synchronzied and a List is only syncrhonzied when it wrapped in SynchronizedList
        List<String> namesSafe = Collections.synchronizedList(new ArrayList(Arrays.asList("John", "Anton", "Heinz")));
    }
}