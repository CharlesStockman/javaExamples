package collections.list;

import java.util.Stack;
import java.util.Vector;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

// Stack extends Vector
// Instead of Stack use ConcurrentLinkedDeque -- Threadsafe
class StackDemo {
    public static void main(String... args) {

        Stack<String> stack = new Stack<>();
        stack.push("Tomoates");
        stack.push("Carrots");
        stack.push("Cucumbers");

        System.out.println(stack.toString());

        while( stack.empty() == false ) {
            System.out.println(stack.pop());
        }
    }
}