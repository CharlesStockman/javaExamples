/**
 * Sorting in Java
 */

public class Sort {

    public void sortedArray() {
        int unsorted = [3,4,1,2];

        // Arrays Sort useds dual pivot Quicksort on primitives (O(n log(n)) and uses stable
        // Iterative implementation on Array of Objects
        System.out.println("Sorted Array is " + Arrays.sort(unsorted));

        // Sort part of an array exclusive ( Sort element 1 and 2 )
        System.out.println("Sort Part of an Array " + Arrays.sort(unsorted,1,3));

        // Sort in Parallel -- uses ForJoin
        // Sound like divide and Conquer
        System.out.println("Sort in Parellel", Arrays.parallelSort(unsorted);

    }

    public void sortList() {
        List<Integer> toSortedList = Ints.asList([3,4,2,1]);

        // Sort -- The Object must implement the Comparable
        System.out.println("Sorting by " + Collections.sort(toSortedList));
        System.out.println("--> " + ArrayUtils.toObject(Collections.sort(toSortedList)));
    }

    public static void main() {
    }
}