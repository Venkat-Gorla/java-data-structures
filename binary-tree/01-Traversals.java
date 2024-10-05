// Common traversal methods of a binary tree

//----------------------------------------
import java.util.Stack;

/**
 * Test program for binary tree
 * topics covered: 
 * - Inorder traversal using iteration
 * - Preorder traversal using iteration
 */
public class ConsoleApp {

    public static void main(String[] args) {
        testTraversals();
    }

    static void testTraversals() {
        // creating a BST to verify the sorted nature of inorder but can be any binary tree
        BinaryTree.Node root = new BinaryTree.Node(2);
        root.left = new BinaryTree.Node(1);
        root.right = new BinaryTree.Node(3);
        root.right.right = new BinaryTree.Node(4);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.inorder();
        binaryTree.preorder();
    }
}

class BinaryTree {

    private Node root;

    static class Node {
    
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void inorder() {
        System.out.print("Inorder traversal: ");

        Node current = root;

        Stack<Node> stack = new Stack<>();

        do {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.value + " ");
                current = current.right;
            }
        } while (current != null || !stack.isEmpty());

        System.out.println();
    }

    public void preorder() {
        System.out.print("Preorder traversal: ");

        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.value + " ");

            if (current.right != null)
                stack.push(current.right);

            if (current.left != null)
                stack.push(current.left);
        }

        System.out.println();
    }
}

/*
Output:
Inorder traversal: 1 2 3 4
Preorder traversal: 2 1 3 4

*/

