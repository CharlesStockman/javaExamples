/**
 * Shows the HashSet
 *
 * Stores unique elements and permits nulls
 * Backed by a HashMap
 *      HashMap Gets Create when HashSet is created
 * Does not maintain Insertiion Order
 * Not Thread Safe
 *
 * Performance : Initial Capacity and Load Factor
 *      A low  initial capacity reduce space complexity , but increase the frequency of reshashing which is expensive
 *      A high initial capacity increases the cost of iteratino and the initial memory consumption
 *
 *      A high initial capacity is good for a large number of entries with little or no iteration
 *      A low initia capacity is good for a a few entries with a lot of iteratino
 *      Default is uually optmized and should work fine.
 *
 * When an element is add the hashcode of the specific object to determine if the element is not in the set already
 * When the bucket is selected then the equals is used ot verify that it is not already in the bucket.
 *
 * HashMap
 *      1. Array of buckets with a default capcity of 16 elements -- Each bucket corresponds to a different hashcode value
 *      2. If the hashcode is the same the objeect get stored in a List that is found in bucket
 *      3. If the load factor is reachecd then a new array is created with twice the size of the previous
 *
 */

pubic class HashSet {
    public void show() {
        Set<String> hashset = new HashSet<>();

        // Insert an element
        // (T) if add / (F) if already there
        // Uses Hashmap.put(e, PRESENT) == null
        Boolean result = hashset.add("Hello World");
        System.out.println("Has element been added -- " + result);
        result = hashset.add("Hello World");
        System.out.println("Has element been added -- " + result);
        result = hashSet.add("Chuck");
        System.out.println("Has an element been addded " + result);

        // Is the element present in a HashSet (true is returned if it is )
        System.out.println("Is the element contained in hashset " + hashset.contains("Hello World");

        // Iterator -- There is no order and the iterators are fail-fast
        // If the hashset is modified at any time unless using the iterators remove a Concurrent Exception is thrown
        //
        Iterator<String> itr = hashset.iterator();
        while (itr.hasNext())
            System.out.println(itr.next()));

        // Remove an Element or all the elements
        hashset.remove("Chuck");
        hashset.clear();

        System.out.println("Is the Hash Table Empty -- " +  hashset.isEmpty());
        System.out.println("Hash Table Size         -- " +  hashset.size());



    }
}