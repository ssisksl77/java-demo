package algorithm.tree.binary_tree_insert;

public class InsertTest {
    public static void main(String[] args) {
        Tree t = new Tree(new Tree.Node(10));
        t.root.left = new Tree.Node(11);
        t.root.left.left = new Tree.Node(7);
        t.root.right = new Tree.Node(9);
        t.root.right.left = new Tree.Node(15);
        t.root.right.right = new Tree.Node(8);

        System.out.print( "Inorder traversal before insertion:");
        t.inorder(t.root);

        int key = 12;
        t.insert(t.root, key);

        System.out.print("\nInorder traversal after insertion:");
        t.inorder(t.root);
    }
}
