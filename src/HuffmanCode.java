import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
public class HuffmanCode {
    private int[] cf = new int[128];
    private PriorityQueue<Helper<BinaryTree<Character>>> pq;
    private BinaryTree<Character> tree;
    private String[] paths = new String[128];
    public HuffmanCode(String in) {
        //Implements the main flow of your program
        //Add private methods and instance variables as needed
        // Find the frequency of characters in the string
        try{
            findFrequency(new FileReader(in));
        } catch (IOException e){
            System.out.println("Something went wrong");
        }
        queueChars();
        tree = constructTree();
        findPaths();
    }

    // Getters and Setters
    public BinaryTree<Character> getTree() {
        return tree;
    }
    public int[] getCf() { return cf; }

    private void findPaths() {
        StringBuilder sb = new StringBuilder();
        findPaths(sb, tree);
    }
    private void findPaths(StringBuilder sb, BinaryTree<Character> t) {
        //TODO: Find the paths to each character.
        if(t == null) {
            System.out.println("An error has occured.");
            return;
        }
        if(sb == null){
            System.out.println("STRINGBUILDER IS NULL!!");
            return;
        }
        if(t.getData() != null) paths[((int)t.getData())] = sb.toString(); // Leaf!

        findPaths(sb.append(0), t.goLeft());
        findPaths(sb.append(1), t.goRight());
    }
    private void findFrequency(FileReader reader) throws IOException {
        BufferedReader r = new BufferedReader(reader);
        int c = r.read();
        while (c != -1){
            cf[c] ++;
            c = r.read();
        }
        r.close();
    }
    private void queueChars(){
        pq = new PriorityQueue<>();
        int i;
        for(i=0; i<cf.length; i++) {
            if(cf[i] != 0) {
                pq.add(new Helper<>(cf[i], new BinaryTree<>((char) i)));
                if (i <= 32 || i == 127) System.out.println(i + " queued with priority: " + cf[i]);
                else System.out.println((char) i + " queued with priority: " + cf[i]);
            }
        }
        System.out.println("Done queuing.");
    }
    private BinaryTree<Character> constructTree(){
        // If the PQ has 1 or 0 you either have your end result or you called method this too early.
        if(pq.size() == 1) return pq.poll().data;
        if(pq.size() == 0) return null;
        // Poll the lowest two trees, combine them, then stick them back in the queue.
        Helper<BinaryTree<Character>> temp0 = pq.poll(), temp1 = pq.poll(),
                result = new Helper<>(temp0.priority + temp1.priority, new BinaryTree<>(temp0.data, '\0' , temp1.data));
        pq.add(result);
        // Call again to keep constructing.
        return constructTree();
    }

    public static class Helper<T> implements Comparable<Helper<T>>{
        int priority;
        T data;

        public Helper(int priority, T data){
            this.priority = priority;
            this.data = data;
        }

        @Override
        public int compareTo(Helper<T> o) {
            return Integer.compare(priority, o.priority);
        }
    }
}
