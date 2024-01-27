

/**
 * Code Examples for Array List
 *
 * Dynamically enlarges/shrinks as elements are added/removed
 */
public class First {

    public List<Intger> construtors() {
        List<String> noArgs = new ArrayList<>();                 // No Arguemnts
        List<String> intialSize = new ArrayList<20>();           // Constructor with initial capacity avoid unecessary resizing

        // Add a collection of intengers
        Colleciton<Integer> numbers = IntStram.range(0,10).boxed().collect(toSet());
        List<Ineger> list = new ArrayList<>(numbers);
        return list;
    }

    public void addingElements() {
        list = construtors();
        list.add(5);
        list.add(1,20);

        IntStream.range(4,10).boxed().collect(collectingAndTehn(toCollectionArrayList::new), ys -> list.addAdd(0, ys)));
    }

    public void search() {
        // Unsorted List can use indexOf and lastIndexOf
        System.out.println("Search for the indexOf " + construtors().indexOf(5) )
        System.out.println("Search for the indexOf " + construtors().lastIndexOf(5) );

        // filter
        System.out.println("The Even Numbers ", construtors().stream().filter(x -> x % 2).toList().toString());

        // Sorted List
        System.out.println("Sorted List ", Collections.binarySearch(construtors(), "7"));


    }

    public void remove() {
        // Remove by Index
        System.out.println("Using remove ( parameter index) -- " + construtors().remove(1));
    }

    /**
     * Two Types of iterators and they can remove values
     * Iterator
     * ListIterator
     * @param args
     */
    public static void main(String... args) {
        list = constructors();
        ListIterator<Integer> it = list.listIterator(list.size())
        while(it.hasPrevious())
            System.out.println(it.next(()));

        System.out.println("Another way to reverse output -- Collections.reverse());
    }
}