package collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

//
// Iterator over a snapshot for the current operation and creates a new array for future access
//
// Features
//    1. Thread Safe with out requiring explicit synchronization
//    2. When an additon is made ( add/remove/set ) then the underlying list is recreatee with the modification, but remains unchanged for read operations
//    3. Immutable
//    4. Good performance for fewer writes
//
// When to Use CopyOnWriteArrayList
//    Read-Mostly Scenarios: When the majority of operations are reads and writes are infrequent, as read operations do not block and do not require synchronization.
//    Snapshot Iterations: When you need to iterate over the list without worrying about concurrent modifications.
//    Thread-Safe Collections: When you need a thread-safe alternative to ArrayList without the overhead of explicit synchronization.
//
// Limitations
//    High Write Costs: Due to the copy-on-write mechanism, write operations (add, set, remove) can be costly in terms of time and memory.
//    Inefficient for Write-Heavy Workloads: Not suitable for scenarios with frequent modifications as each write involves copying the entire array.
//
public class CopyOnWriteArrayListDemo {
    public static void main(String... args) {
        List<String> names = new CopyOnWriteArrayList<>(Arrays.asList("John", "Anton", "Heinz", "John"));
        names.sort(null);       // Using natural order to sort
        names.forEach( ( name -> {
            System.out.println(name);
            if (name.contains("o")) names.remove(name); }
        ));
        System.out.println(names);
    }
}