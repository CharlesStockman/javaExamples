package collections.list;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * A function sort has a parameter Comparartor and when null does natural sorting
 *
 * Can also use Comparator<Student> if the lambda function does not know it minutes
 *
 * On a speedtest done with 10_000_000 the ArrayList took 5.5 seconds and the Link List is 5.7 and CopyOnWrite was 5.3
 *     Interesting -- Sorting is close to the array but you have two move node around -- How is this possible
 *      It copies it into the array and then sorts it and then copies it back to the link list
 *
 * A sorted list ArrayList = 183, Link List = 546, CopyOnArrayList=5389
 *
 * For primitives duel pivot quicksort which has two pivots
 * Belief the default sorting algorithm is quicksort, but merge sort can be used ( LegacyMergeSort  which was slower)
 *
 * parallel on sorted list might take a really long time more than sequential sort.
 * it takes to split the data, combine the sublist back.
 * Parallel sort uses TimSort -- The algorithm finds subsequences of the data that are already ordered (runs) and uses them to sort the remainder more efficiently.
 *
 */
class SortingListDemo {

    private static class Student implements Comparable<Student> {
        private final int year;
        private final String name;

        public Student(int year, String name) {
            this.year = year;
            this.name = name;
        }

        public int compareTo(Student o) {
            //System.out.println("Calling compareTo");
            int result = Integer.compare(year, o.year);
            return ((result == 0) ?  name.compareTo(o.name) : result);
        }

        public String toString() {
            return "Student " + name + " from year " + year + "\n";
        }

        public int getYear() { return year; }
        public String getName() { return name; }
    }

    public static void main( String[] args) {

        // Sorting a list by natural order
        List<String> names = Arrays.asList("John", "Anton", "Heinz");
        names.sort(null);
        System.out.println("names = " + names);

        List<Student> names2 = Arrays.asList(
                new Student(1, "John"),
                new Student(2, "John"),
                new Student(3, "Heinz"),
                new Student(3, "Anton"));
        names2.sort(null);
        System.out.println("Sorted List using a Comparable Interface is " + names2);

        // Sorting the list using the Collections methods
        SortingListDemo.shuffle(names2);
        Collections.sort(names2);
        System.out.println("Sorting using the Collections.sort" + names2);

        // Sorting the list in reverse order
        Collections.sort(names2, Collections.reverseOrder());
        System.out.println("The Sorted and Reverse List is " + names2);

        // Sorting the list using a lambda for the Comparator
        SortingListDemo.shuffle(names2);
        names2.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted using a lambda -- " + names2);

        // Have the Comparator create the function that does the Comparator methods
        SortingListDemo.shuffle(names2);
        names2.sort(Comparator.comparingInt(Student::getYear).thenComparing(Student::getName));
        System.out.println("Sorted using Comparator comparing functions and thenCompare -- " + names2);

        // Have the Comparator create the function that does the Comparator methods
        SortingListDemo.shuffle(names2);
        Comparator<Student> comparator = Comparator.comparingInt(Student::getYear).thenComparing(Student::getName);
        names2.sort(comparator);
        System.out.println("Sorted using Comparator comparing functions and thenCompare -- " + names);

        // Using a variable for the Comparator that includes reversing the list
        // Have the Comparator create the function that does the Comparator methods
        SortingListDemo.shuffle(names2);
        names2.sort(Comparator.comparingInt(Student::getYear).thenComparing(Student::getName).reversed());
        System.out.println("Sorted using Comparator comparing functions and thenCompare -- " + names2);
    }

    /**
     * An example of manipulating the arrayList when you need to change internal data.  Could be useful if creating
     * another data structure or tyring another a modification to a data structure
     *
     * @param <E>  -- Any Type the user needs
     */
    private static class ParallelSortingArrayList<E> extends ArrayList<E>  {
            private static Field elementDataField = null;
            static {
                try {
                    elementDataField = ArrayList.class.getDeclaredField("elementData");
                    elementDataField.setAccessible(true);
                } catch (ReflectiveOperationException exception) {
                  throw new Error(exception);
                }
            }

            public void sort(Comparator<? super E> c) {
                try {
                    final int expectedModCount = modCount;
                    Arrays.sort((E[]) elementDataField.get(this), 0, size(),c);
                    if ( modCount != expectedModCount )
                        throw new ConcurrentModificationException();
                    modCount++;            
                 } catch( IllegalAccessException e ){
                    throw new IllegalStateException(e);
                 }

            }
    }

    /**
     * Shuffles the list so we can try a new way of sorting it.
     *
     * @param list   -- The list to be sorted
     * @param <T>    -- The type of data in the list
     * @return nothing, but the side effect is the list is sorted
     */
    private static <T> void shuffle(List<T> list ) {
        Collections.shuffle(list);
        System.out.println("The unsnorted list (using shuffle ) -- " + list);
    }

    /**
     * Create a list of random primitive random integers
     *
     * @param amount -- How many integers to create.
     * @return A list contains a list of integers
     */
    public static List<Integer> createList(int amount ) {
        return ThreadLocalRandom.current().ints(amount).parallel().boxed().collect(Collectors.toList());
    }


}