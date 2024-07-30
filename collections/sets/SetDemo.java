package collections.sets;

import java.util.Set;
import java.util.HashSet;

/**
 *
 */
public class SetDemo {

    public static void main(String... args ) {

        // Will cause an ImllegalArgumentException
        // Set<Integer> set = Set.of(1,2,3,3,3,3);

        // Constructing a Set
        Set<Integer> set2 = new HashSet<Integer>();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(1);
        set2.add(1);
        set2.add(1);
        set2.add(1);
        set2.add(4);

        System.out.println("set = " + set2);

        // Constructing a set
        Set<Integer> oddBelowTen = new HashSet<Integer>();
        oddBelowTen.add(1);
        oddBelowTen.add(3);
        oddBelowTen.add(5);
        oddBelowTen.add(7);
        oddBelowTen.add(9);
        System.out.println("Odd below 10 -- " + oddBelowTen);

        // Constructing a set
        Set<Integer> evenBelowTen = new HashSet<Integer>();
        evenBelowTen.add(0);
        evenBelowTen.add(2);
        evenBelowTen.add(4);
        evenBelowTen.add(6);
        evenBelowTen.add(8);
        System.out.println("Even below 10 -- " + evenBelowTen);

        // Union -- Use addAll
        Set<Integer> numberBelowTen = new HashSet<>(oddBelowTen);
        numberBelowTen.addAll(evenBelowTen);
        System.out.println("Unions of the Sets below 10 -- " + numberBelowTen.toString());

        Set<Integer> primesBelowTen = new HashSet<>();
        primesBelowTen.add(2);
        primesBelowTen.add(3);
        primesBelowTen.add(5);
        primesBelowTen.add(7);
        System.out.println("Primes below ten = " + primesBelowTen);

        // Union -- Use retainAll
        Set<Integer> intersectionPrimesAndEven = new HashSet<>(primesBelowTen);
        intersectionPrimesAndEven.retainAll(evenBelowTen);
        System.out.println("Intersection Primes And Even = " + intersectionPrimesAndEven);

        // Difference -- use removeAll for A - b where  A is primes Below Ten and B is odds Below 10
        Set<Integer> primesLessOdds =  new HashSet<>(primesBelowTen);
        primesLessOdds.removeAll(oddBelowTen);
        System.out.println("prime less odds " + primesLessOdds);

        // Difference -- use removeAll for B - A where A is primes Below Ten and B is odds Below 10
        Set<Integer> oddsLessPrimes =  new HashSet<>(oddBelowTen);
        oddsLessPrimes.removeAll(primesBelowTen);
        System.out.println("odds less prime " + oddsLessPrimes);

    }
}