
/**
 * Test program for binary search tree
 * topics covered: 
 * - insert 
 * - contains
 * - Node deletion
 * - pretty print that shows the tree structure 
 * - Note: tree balancing is a separate topic and Not discussed here
 */
public class ConsoleApp {

    public static void main(String[] args) {
        testBinarySearchTree();
    }

    static void testBinarySearchTree() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        int[] keys = new int[]{20, 10, 40, 30};
        for (int key : keys) {
            binarySearchTree.insert(key);
        }

        System.out.println("Binary search tree created");
        binarySearchTree.prettyDfs();
        System.out.println();
        
        testContains(binarySearchTree, keys);

        System.out.println("\nTesting contains for some invalid keys");
        int[] invalidKeys = new int[]{50, 60};
        testContains(binarySearchTree, invalidKeys);

        int[] keysToDelete = new int[]{40, 20};
        testDeletion(binarySearchTree, keysToDelete);
    }

    static void testContains(BinarySearchTree binarySearchTree, int[] keys) {
        for (int key : keys) {
            if (binarySearchTree.contains(key)) {
                System.out.println("Key " + key + " is found inside the tree");
            } else {
                System.out.println("Key " + key + " is *Not* found inside the tree");
            }
        }
    }

    static void testDeletion(BinarySearchTree binarySearchTree, int[] keysToDelete) {
        for (int key : keysToDelete) {
            binarySearchTree.deleteNode(key);
            System.out.println("\nPrinting tree after deleting key " + key);
            binarySearchTree.prettyDfs();
        }
    }
}

class BinarySearchTree {

    private Node root;

    class Node {
    
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        if (root == null) return false;
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void deleteNode(int value) { 
        root = deleteNode(root, value); 
    }

    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;

        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {
                // leaf node
                return null;                
            } else if (currentNode.left == null) {
                return currentNode.right;
            } else if (currentNode.right == null) {
                return currentNode.left;
            } else {
                // Node with two children is being deleted, find it's Inorder successor
                // that will take it's place after the deletion.
                // The Inorder successor is the left most node in the right sub-tree

                Node inorderSucc = leftMost(currentNode.right);
                currentNode.value = inorderSucc.value;
                currentNode.right = deleteNode(currentNode.right, inorderSucc.value);
            }
        }

        return currentNode;
    }

    private Node leftMost(Node current) {
        while (current != null && current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void prettyDfs() {
        prettyDfs(root, 0, "root");
    }

    private void prettyDfs(Node root, int depth, String qualifier) {
        if (root == null)
            return;

        for (int counter = 0; counter < depth; ++counter) {
            System.out.print("   |");
        }

        System.out.println("-->" + root.value + "(" + qualifier + ")");

        prettyDfs(root.left, depth + 1, "L");
        prettyDfs(root.right, depth + 1, "R");
    }
}

/*
Output:
Binary search tree created
-->20(root)
   |-->10(L)
   |-->40(R)
   |   |-->30(L)

Key 20 is found inside the tree
Key 10 is found inside the tree
Key 40 is found inside the tree
Key 30 is found inside the tree

Testing contains for some invalid keys
Key 50 is *Not* found inside the tree
Key 60 is *Not* found inside the tree

Printing tree after deleting key 40
-->20(root)
   |-->10(L)
   |-->30(R)

Printing tree after deleting key 20
-->30(root)
   |-->10(L)
*/
