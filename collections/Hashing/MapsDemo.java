package collections.maps;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Strategies for clashes
 *      Could put the value in the next bucket -- Need the key
 *      Could have a list of elements, array of elements which could be O(n) look up
 *                Can use another datastructure such as Tree so you can be sure performance is O(logn)
 *
 * How do you distribute these element throughout the object array.
 *      Make the Hash Size a prime number -- problem % is the most expensive operation   // Experiment why does divide take so many clock cycles
 *      New way: Make the Hash Bucket a Power of two and the use the
 *
 * The problem with using List is that it goes from constant time to linear time
 *
 */
public class MapsDemo {
    public static class BasicHashTable<K,V> {
        // private final Object[] values = new Object[101]; -- old way prime numbers
        private final Object[] values = new Object[128];
        public void put(K key, V value) {
            values[calculatePosition(key)] = value;
        }

        public V get(K key) {
            int pos = calculatePosition(key);
            return (V) values[pos];
        }

        private int calculatePosition(K key) {
            int hash = key.hashCode();
            // int pos = hash % values.length; old-way prime numbers
            int pos = hash & 127;
            if ( pos < 0 ) pos += values.length;
            System.out.println("For the Key " + key + " and the hash is " + hash +" the pos is " + pos);
            return pos;
        }

        public String toString() {
            return Stream.of(values).
                    filter( Objects::nonNull).
                    map(Object::toString).
                    collect(Collectors.joining(", ", "{", "}" ));
        }

    }
    public static void main(String... args ) {

        BasicHashTable<Integer, String> numbers = new MapsDemo.BasicHashTable<>();
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(42, "THE number");
        numbers.put(243, "two-four-three");

        System.out.println("numbers = " + numbers);

        System.out.println("Hello World");
    }
}
