package collections.sets;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

/**
 * A TreeSet is sorted and does not have duplicates.
 *
 * TreeSet is baded on the Red Black Tree
 *    1. adding 0 to 15 values
 *
 *    Lets take 0 which becomes the root
 *
 *    Lets add 1 the tree looks like
 *          0
 *           \
 *            1
 *
 *    Lets add 2 the tree looks like
 *
 *          0
 *           \
 *            1
 *             \
 *              2
 *
 *   Lets add 3 the tree looks like
 *
 *          0
 *           \
 *            1
 *             \
 *              2
 *               \
 *                3
 *
 *  Lets say you were given 11, 2 ,7 m, 8, 4
 *
 *             5
 *           /   \
 *          2    11
 *           \   /
 *            4 7
 *               \
 *                8
 *
 *  Balancing is import since the bigger the depth of the tree the longer the worst case scenario takes.
 *  which would provide O(n) look up ( linear performance ).  With a balanced binary tree it should be O(logn)
 *
 *  Red Black Tree -- Balancing General Tree ( Sorted, Throws away duplicates, Reasonably balanced)
 *  When you want to get a better depth in the tree try randomnizing
 *          Tree 1 -- Add One Million Integers sequentially the depth was 37   -- data is inserted in natural order
 *          Tree 2 -- Add Randomly one million integers the depth was 24
 *
 * Stop at 24:47
 */
public class TreeSetDemo {

    public static void main(String args[]) {

            // Will return an Error from javac since ArrayList is not comparable.
            // ArrayList<String> data = new ArrayList<>();
            // data.add("Chuck");
            // Set<ArrayList<String>> test = new TreeSet<>(data);

            Set<Integer> oddsBetweenMinusTenAndTen = new TreeSet<>();
            oddsBetweenMinusTenAndTen.addAll(Arrays.asList(-9, -7, -5, 5, -1 , -3, 1, 1, 1, 9, 7, 3));
            System.out.println("Odds Between Minuse Ten and Ten" + oddsBetweenMinusTenAndTen);

            TreeSet<Integer> experiment1 = new TreeSet<>(IntStream.range(0,1_000_000).boxed().collect(Collectors.toSet()));
            System.out.println("The tree should have 1_000_000 nodes " + experiment1.size());






    }
}