package algorithm.tree.binary_search_tree2;

/**
 * 삭제 기능 추가
 */
public class BinarySearchTree {
    class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
        }
    }

    Node root;

    BinarySearchTree() {

    }

    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) {
            return node; // X
        }

        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.right;
            }

            // 오른쪽에서 자식을 가져옴.
            // node with two children: Get the inorder successor
            // in the rhigt subtree
            node.key = minValue(node.right);
            // delete the inorder successor
            node.right = deleteRec(node.right, node.key);
        }

        return node;
    }
    int minValue(Node node) {
        int minv = node.key;
        while (node.left != null) {
            minv = node.left.key;
            node = node.left;
        }
        return minv;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    void inorder() {
        inorderRec(this.root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.key);
            inorderRec(node.right);
        }
    }



    //////
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("\nDelete 20");
        tree.deleteKey(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
    }
}
