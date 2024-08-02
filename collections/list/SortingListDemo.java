package collections.list;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

/**
 * A function sort has a parameter Comparartor and when null does natural sorting
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
    }

    public static void main( String[] args) {
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

        SortingListDemo.shuffle(names2);
        Collections.sort(names2);
        System.out.println("Sorting using the Collections.sort" + names2);

        Collections.sort(names2, Collections.reverseOrder());
        System.out.println("The Sorted and Reverse List is " + names2);

        SortingListDemo.shuffle(names2);
        names2.sort( (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted using a lambda -- " + names2);
    }

    private static <T> void shuffle(List<T> list ) {
        Collections.shuffle(list);
        System.out.println("The unsnorted list (using shuffle ) -- " + list);
    }

    // Stop at 17:01

}