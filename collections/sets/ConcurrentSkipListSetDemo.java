package collections.sets;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;

/**
 * SkipList -- A list when there are multiple layers of links.  Each layer of links skip a different number of nodes
 * Can provide O(logn) for searching
 *
 * Characteristics : Sorted Set and thread safe.
 *
 * Use : When you need a Thread Safe Sorted List
 *
 * Used More Memory than a Tree Set
 *
 * Used when keeping a list of CachedAddresses that have to expire
 */
class ConcurrentSkipListSetDemo {
    public static void main(String... args ) {

        ConcurrentSkipListSet<Integer>  numbers = new ConcurrentSkipListSet<>();
        ThreadLocalRandom.current().ints(100).forEach( i -> numbers.add(i));
        System.out.println("Number of nodes in List is " + numbers.size());
        System.out.println(numbers);

        System.out.println("------------------");

        ConcurrentSkipListSet<Integer> reversedNumbers = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        ThreadLocalRandom.current().ints(100).forEach( i -> reversedNumbers.add(i));
        System.out.println(reversedNumbers);


    }
}