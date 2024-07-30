package collections.array;

import java.util.Arrays;

// Data Structures -- 2 Tyes ( Arrays ( Hashmap, ArrayList) or Linked Structure ( Link List , Tree )
// Array Strcutures more compact and faster to read
public class Array {
    // Arrays  most compact way of storing information
    // For every element it must follow 8 byte boundary
    public static void main(String... args ) {
        int[] values = {1, 2, 3};
        // int[] (Object header ) 12 bytes ( 64-bit machin wth  compresdOops ( pointers are smaller ) --
        // length is 4 bytes
        //  3 * 4 bytes = 12 bytes
        //  12 + 4 + 12 = 28 which is rounded up to 32 bytes
        //  O(n) for space constant
        int[] values2 = new int[1000];
        // int[] is 12 bytes
        // length is 4 bytes
        // 1000 * 4 bytes for values
        // 12 + 4 + 4000 = 1016
        short[] smaller;   // Two Bytes per entry
        byte[] bytes; // 1 byte per entry

        // Copy the data from one array to another
        String[] names = {"John", "Dane", "Dora" };
        String[] newNames = new String[names.length + 1];
        System.arraycopy(names, 0, newNames, 0, names.length);

        // Multidimensional Array
        // Not stored as a matrix -- store as an array of other arrays and uses a lot of memory since it has multiple arrays
        // favor single arrays;
        int[][][][] quadro = {{{{1,2},{3,4}}}};
        int[][][] sub = quadro[0];
        int[][] subSub = sub[0];
        int[] subSubSub = subSub[0];
        int subSubSubSub = subSubSub[0];

        // deepCopy -- Dislay arrays with two or more dimnensinos
        // toString -- Single disploay array
        System.out.println("quadro = " + Arrays.deepToString(quadro));
        System.out.println("sub = " + Arrays.deepToString(sub));
        System.out.println("subSub = " + Arrays.deepToString(subSub));
        System.out.println("subSubSub = " + Arrays.toString(subSubSub));
        System.out.println("subSubSuSub = " + subSubSubSub);

        // Fatest way to read memory -- from beginning to end -- load the array in cache lines which are 64 bytes at a time
    }

}