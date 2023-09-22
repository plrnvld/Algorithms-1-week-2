import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 1 )
            throw new IllegalArgumentException("Supply exactly 1 number");

        var k = Integer.parseInt(args[0]);       
        var randomized = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            var temp = StdIn.readString();
            randomized.enqueue(temp);            
        }

        if (k > randomized.size())
            throw new IllegalArgumentException("Number cannot be bigger that num items");

        for (var i = 0; i < k; i++)
            System.out.println(randomized.dequeue());
    }
}