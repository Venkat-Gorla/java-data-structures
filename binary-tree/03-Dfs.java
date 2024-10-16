import java.util.ArrayList;
import java.util.stream.IntStream; // rangeClosed

/**
 * Binary tree Depth first search usage
 * - DFS is the same as preorder traversal, we will understand this with the help of some examples
 * - Given a key in the tree, get its connecting path from the root
 * - Print all root to leaf paths in the tree
 */
public class ConsoleApp {

    public static void main(String[] args) {
        testGetConnectingPath();
        System.out.println();
        testRootToLeafPaths();
    }

    static void testGetConnectingPath() {
        BinaryTree binaryTree = createBinaryTree();
        int[] searchKeys = IntStream.rangeClosed(3, 6).toArray();
        for (int key : searchKeys) {
            testGetConnectingPath(binaryTree, key);
        }
    }

    static void testRootToLeafPaths() {
        BinaryTree binaryTree = createBinaryTree();
        var rootToLeafPaths = binaryTree.getRootToLeafPaths();
        System.out.println("Printing root to leaf paths in the tree:");
        System.out.println(rootToLeafPaths);
    }

    static BinaryTree createBinaryTree() {
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

        return new BinaryTree(root);
    }

    static void testGetConnectingPath(BinaryTree binaryTree, int key) {
        ArrayList<Integer> connectingPath = new ArrayList<>();
        var found = binaryTree.getPathFromRoot(key, connectingPath);
        if (found) {
            System.out.println("Connecting path for key " + key + " is " + connectingPath);
        }
        else {
            System.out.println("Connecting path for key " + key + " is *Not* found");
        }
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

    public boolean getPathFromRoot(int key, ArrayList<Integer> connectingPath) {
        return getPathFromRoot(root, key, connectingPath);
    }

    public ArrayList<ArrayList<Integer>> getRootToLeafPaths() {
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<ArrayList<Integer>> rootToLeafPaths = new ArrayList<>();

        getRootToLeafPaths(root, path, rootToLeafPaths);

        return rootToLeafPaths;
    }

    private boolean getPathFromRoot(Node root, int key, ArrayList<Integer> connectingPath) {
        if (root == null)
            return false;

        connectingPath.add(root.value);

        if (root.value == key || 
            getPathFromRoot(root.left, key, connectingPath) || 
            getPathFromRoot(root.right, key, connectingPath)) {
            return true;
        } 
        else {
            connectingPath.remove(connectingPath.size() - 1);
            return false;
        }
    }

    private void getRootToLeafPaths(
        Node root, 
        ArrayList<Integer> path, 
        ArrayList<ArrayList<Integer>> rootToLeafPaths) {
            if (root == null)
                return;

            path.add(root.value);

            if (isLeaf(root)) {
                rootToLeafPaths.add(new ArrayList<>(path));
            }
            else {
                getRootToLeafPaths(root.left, path, rootToLeafPaths);
                getRootToLeafPaths(root.right, path, rootToLeafPaths);
            }

            path.remove(path.size() - 1);
    }

    private boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
}

/*
Output:

Connecting path for key 3 is [1, 3]
Connecting path for key 4 is [1, 2, 4]
Connecting path for key 5 is [1, 3, 5]
Connecting path for key 6 is *Not* found

Printing root to leaf paths in the tree:
[[1, 2, 4], [1, 3, 5]]
*/

