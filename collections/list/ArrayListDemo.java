package collections.list;

import java.util.ArrayList;

// Array List backed ar4ray will grow but they do not shrink unless the developer uses trimToSize();
//
// An Array List starts with 10 elements and when the size is exceeded a new array wiild be created and the content
// copied where the name is the size of the original array * 1.5
//
// trimToSize -- usually not used as much since we have linked list and queues ( ArrayList do not make good queues )
//
// Want to make a new ArrayList instead of doing clear() for garbage collection is optimized for creating objects and
// throwing them away so long as you don't hold to them too long or they grow too big.
//
// A good optimization is to construct the ArrayList with the capacity that is close the amount of data it will hold.
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<String> seasons = new ArrayList<>();
        seasons.add("Winter");
        seasons.add("Spring");
        seasons.add("Summer");
        seasons.add("Fall");
        System.out.println("Index of Summer = " + seasons.indexOf("Summer"));

        seasons.remove("Fall");
        System.out.println("The reminaing seasons are " + seasons.toString());
        System.out.println("Seasons contains summer -- " + seasons.contains("Summer"));
        System.out.println("Seasons contains winter -- " + seasons.contains("Fall"));

        seasons.removeIf( season -> season.equals("Summer"));
        System.out.println("The Seasons Array is " + seasons.toString());

        //

    }
}

