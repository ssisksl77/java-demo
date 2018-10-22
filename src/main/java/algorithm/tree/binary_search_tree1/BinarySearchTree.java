package algorithm.tree.binary_search_tree1;

/**
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 */
public class BinarySearchTree {
    class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }
    Node insertRec(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key){
            node.right = insertRec(node.right, key);
        }

        return node;
    }

    void inorder() {
        inorderRec(this.root);
    }

    private void inorderRec(Node root) {
        if (root == null) return;

        inorderRec(root.left);
        System.out.println(root.key);
        inorderRec(root.right);
    }


    /**
     *  TEST
     */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50 );
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        tree.inorder();
    }
}
