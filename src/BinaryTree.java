public class BinaryTree<T> {
    private Node head;
    public BinaryTree(T c) {
        head = new Node(c);
    }

    public BinaryTree( BinaryTree<T> tree1, T c,  BinaryTree<T> tree2){
        head = new Node(tree1.head, c, tree2.head);
    }

    private BinaryTree(Node n){
        head = n;
    }

    public BinaryTree<T> goLeft(){
        return new BinaryTree<>(head.left);
    }

    public T getData(){
        return head.data;
    }

    public BinaryTree<T> goRight(){
        return new BinaryTree<>(head.right);
    }

    public String postOrderString() {
        return postOrderString(head);
    }
    private String postOrderString(Node n){
        if(n == null) return "";
        String l = postOrderString(n.left);
        String r = postOrderString(n.right);
        return l + (l.length() > 0 ? " " : "") + r + (r.length() > 0 ? " " : "") + n.data;
    }

    //This class makes no reference to Huffman Encoding
    //Add methods and instance variables for a general
    //binary tree class
    //You only have to add methods you need for the homework
    //but the methods should be general binary tree methods
    //they should not refer to Huffman Encoding
    private class Node {
        private Node left;
        private T data;
        private Node right;

        private Node(T d){
            this(null, d, null);
        }

        private Node(Node L, T d, Node R) {
            left = L;
            data = d;
            right = R;
        }
    }
}