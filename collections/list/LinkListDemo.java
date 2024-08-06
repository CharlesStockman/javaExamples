package collections.list;

// More Expensive with Array since each node need two pointer ( previous and next )
// Expensive when accessing in the middle
//
//
//
// Where to use:
//      Graph Adjacency List Representation -- Graphs are often represented using adjacency lists, where each vertex has a list of adjacent vertices. Linked lists can be used to store these lists efficiently.
//      Browser History Management -- Browsers use linked lists to manage the history of visited pages. Each page visit is a node, and back/forward operations traverse this linked list.
//      Memory Management -- In operating systems, linked lists are used for memory management tasks like managing free memory blocks, where each node represents a memory block and its size.
//      Polynomial Arithmetic -- Polynomials can be represented using linked lists where each node represents a term with its coefficient and exponent. Operations like addition and multiplication of polynomials can be efficiently performed using this representation.

public class LinkListDemo {

    private final static class MyLinkList<E> {
        private final static class Node<E> {
            private Node<E> previous, next;
            private E e;

            public Node(E e) {
                this.e = e;
            }
        }

        private Node<E> head;
        private Node<E> tail;

        public void add(E e) {
            if ( head == null )
                head = tail = new Node<E>(e);
            else {
                Node<E> node = new Node<E>(e);
                node.previous = tail;
                tail.next = node;
                tail = node;
            }
        }
    }
    public static void main(String... args) {
        MyLinkList<Integer> queue = new MyLinkList<>();

    }
}