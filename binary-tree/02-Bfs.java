import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary tree Breadth first search traversal
 * - BFS is also referred to as level order traversal
 * - we will also see how to print one level at a time and use it to compute the height of the tree
 * - can assume root of the tree is at level 0
 */
public class ConsoleApp {

    public static void main(String[] args) {
        testBfs();
        System.out.println();
        testBfsPrintLevels();
    }

    static void testBfs() {
        /*
            1
          /    \
          2     3
           \    
            4  

        */

        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.right = new BinaryTree.Node(4);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.bfs();
    }

    static void testBfsPrintLevels() {
        /*
            1
          /    \
          2     3
           \    /
            4  5

        */

        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.right = new BinaryTree.Node(4);
        root.right.left = new BinaryTree.Node(5);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.bfsPrintLevels();
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

    public void bfs() {
        System.out.print("Printing Bfs of the binary tree: ");

        if (root == null)
            return;

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            Node current = nodeQueue.remove();
            System.out.print(current.value + " ");

            if (current.left != null)
                nodeQueue.add(current.left);

            if (current.right != null)
                nodeQueue.add(current.right);
        }

        System.out.println();
    }

    public void bfsPrintLevels() {
        System.out.println("Printing Bfs of the binary tree, one level at a time:");

        if (root == null)
            return;

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int height = 0;
        while (!nodeQueue.isEmpty()) {
            for (int numNodes = nodeQueue.size(); numNodes > 0; numNodes--) {
                Node current = nodeQueue.remove();
                System.out.print(current.value + " ");

                if (current.left != null)
                    nodeQueue.add(current.left);

                if (current.right != null)
                    nodeQueue.add(current.right);
            }

            // the for loop will process one level of nodes, so this is where we increment the height
            height++;

            System.out.println();
        }

        System.out.println("Height of the binary tree is " + height);
    }
}

/*
Output:
Printing Bfs of the binary tree: 1 2 3 4

Printing Bfs of the binary tree, one level at a time:
1
2 3
4 5
Height of the binary tree is 3

*/

