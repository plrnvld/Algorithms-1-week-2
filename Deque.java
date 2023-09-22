import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr;

        DequeIterator() {
            curr = first;
        }
        
        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
                
            var result = curr.item;
            curr = curr.next;
            return result;
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");

        size++;

        var node = new Node();
        node.item = item;
        node.next = first;
        node.prev = null;

        if (first == null)
            first = last = node;
        else {
            first.prev = node;
            first = node;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");

        size++;

        var node = new Node();
        node.item = item;
        node.next = null;
        node.prev = last;

        if (last == null)
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (first == null)
            throw new NoSuchElementException("Deque is empty, no first item available");

        size--;

        var result = first.item;
        first = first.next;

        if (first != null)
            first.prev = null;

        if (size == 0)
            first = last = null;

        return result;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (last == null)
            throw new NoSuchElementException("Deque is empty, no last item available");

        size--;

        var result = last.item;
        last = last.prev;

        if (last != null && last.next != null)
            last.next = null;

        if (size == 0)
            first = last = null;

        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        var deque = new Deque<Integer>();

        deque.addFirst(5);
        deque.addFirst(3);
        deque.addLast(7);
        deque.addLast(9);
        deque.addFirst(2);        

        System.out.println("Size = " + deque.size());

        System.out.println("---- Show 5 items");
        for (var item : deque) {
            System.out.println(item);
        }

        deque.removeLast();
        deque.removeFirst();

        System.out.println("---- Removed last and first");
        for (var item : deque) {
            System.out.println(item);
        }

        deque.addLast(10);
        deque.addFirst(1);

        System.out.println("---- Add two new");
        for (var item : deque) {
            System.out.println(item);
        }

        for(var i = 0; i < 3; i++)
            deque.removeFirst();

        for (var i = 0; i < 2; i++)
            deque.removeLast();

        System.out.println("---- Removed all");
        for (var item : deque) {
            System.out.println(item);
        }

        deque.addLast(77);
        deque.addFirst(66);

        System.out.println("---- Add 2 new items");
        for (var item : deque) {
            System.out.println(item);
        }
    }
}