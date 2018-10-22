package algorithm.tree.binary_tree;

public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);

    }
}
