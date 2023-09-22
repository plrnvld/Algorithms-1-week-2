import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQueueIterator implements Iterator<Item> {

        int[] randomIndexes;
        int index;

        RandomizedQueueIterator() {
            randomIndexes = StdRandom.permutation(size());
            index = 0;
        }
        
        public boolean hasNext() {
            return index < randomIndexes.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            var nextPos = randomIndexes[index++];
            return (Item)items[nextPos];
        }
    }

    private int end;
    private int capacity;

    private Object[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        end = 0;
        capacity = 2;
        items = new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return end == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return end;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");

        if (end >= capacity)
            doubleCapacity();        

        items[end] = item;

        end++;
    }

    private void doubleCapacity() {
        var newCapacity = capacity > Integer.MAX_VALUE / 2
            ? Integer.MAX_VALUE
            : capacity * 2;
            
        var newArray = new Object[newCapacity];
        
        for (var i = 0; i < items.length; i++)
            newArray[i] = items[i];

        items = newArray;

        capacity = newCapacity;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();

        if (size() <= capacity / 4)
            halveCapacity();

        var randomIndex = StdRandom.uniformInt(end);
        var lastIndex = end - 1;        
        var result = (Item)items[randomIndex];
        
        if (randomIndex != lastIndex) 
            items[randomIndex] = items[lastIndex];
                
        items[lastIndex] = null;
        end--;

        return result;
    }

    private void halveCapacity() {
        var newCapacity = capacity / 2 < 2
            ? 2
            : capacity / 2;
            
        var newArray = new Object[newCapacity];
        
        for (var i = 0; i < newArray.length; i++)
            newArray[i] = items[i];
        
        items = newArray;

        capacity = newCapacity;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0)
            throw new NoSuchElementException();

        var randomIndex = StdRandom.uniformInt(end);

        return (Item)items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        var randomized = new RandomizedQueue<Integer>();

        randomized.enqueue(1);
        randomized.enqueue(2);
        randomized.enqueue(3);
        randomized.enqueue(4);
        randomized.enqueue(5);
        randomized.enqueue(6);
        randomized.enqueue(7);
        randomized.enqueue(8);
        randomized.enqueue(9);
        
        System.out.println("---- Show elements");
        for (var item : randomized) {
            System.out.println(item);
        }

        System.out.println("---- Show elements again");
        for (var item : randomized) {
            System.out.println(item);
        }

        randomized.dequeue();
        randomized.dequeue();
        randomized.dequeue();

        System.out.println("---- Show elements after removing 3");
        for (var item : randomized) {
            System.out.println(item);
        }

        System.out.println("Sampling: " + randomized.sample());
        System.out.println("Sampling: " + randomized.sample());
        System.out.println("Sampling: " + randomized.sample());
        System.out.println("Sampling: " + randomized.sample());
        System.out.println("Sampling: " + randomized.sample());

        System.out.println("Size =  " + randomized.size());
    }
}
