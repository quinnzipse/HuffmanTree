import java.util.*;
public class BinaryTree<T> {
    private Node head;
    public BinaryTree(T c) {
        head = new Node(c);
    }

    public BinaryTree( BinaryTree<T> tree1, T c,  BinaryTree<T> tree2){
        head = new Node(tree1.head, null, tree2.head);
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

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public T getData() {
            return data;
        }
    }
}