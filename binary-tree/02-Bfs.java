import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary tree Breadth first search traversal
 * - BFS is also referred to as level order traversal
 * - can assume root of the tree is at level 0
 */
public class ConsoleApp {

    public static void main(String[] args) {
        testBfs();
    }

    static void testBfs() {
        BinaryTree.Node root = new BinaryTree.Node(1);
        root.left = new BinaryTree.Node(2);
        root.right = new BinaryTree.Node(3);
        root.left.right = new BinaryTree.Node(4);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.bfs();
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
}

/*
Output:
Printing Bfs of the binary tree: 1 2 3 4

*/

