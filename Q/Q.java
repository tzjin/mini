/* Q.java
 *
 * Tony Jin
 * 4/4/14
 *
 * Description:  the concise Queue
 *
 * Dependencies: java.util.LinkedList
 */

import java.util.LinkedList;

public class Q<Item> {

    private LinkedList<Item> q;

    /* Constructors */

    // initializing an empty queue
    public Q() {
        q = new LinkedList<Item>();
    }

    // initalizing a queue populated with array elements
    public Q(Item [] items) {
        q = new LinkedList<Item>();
        for (int i = 0; i < items.length; i++)
            nq(items[i]);
    }

    // initializing a queue based on an iterator's elements
    public Q(Iterable<Item> items) {
        q = new LinkedList<Item>();
        for (Item i : items) 
            nq(i);
    }


    /* Access Methods */

    // isEmpty
    public boolean em() {
        return (q.size() == 0);
    }

    // enqueue
    public void nq(Item item) {
        q.add(item);
    }

    // dequeue
    public Item dq() {
        return q.remove();
    }

    // see (peek)
    public Item c() {
        return q.peek();
    }


    /* Tests */
    public static void main (String [] args) {
        Q<String> q = new Q<String>(args);

        while (!q.em())
            System.out.print(q.dq() + " ");
        System.out.println();
    }
}