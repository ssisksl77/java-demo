package algorithm.tree.zigzag;
// https://www.geeksforgeeks.org/zigzag-tree-traversal/
public class ZigZag {
        public static void main(String[] args)
        {
            BinaryTree tree = new BinaryTree();
            tree.rootNode = new BinaryTree.Node(1);
            tree.rootNode.leftChild = new BinaryTree.Node(2);
            tree.rootNode.rightChild = new BinaryTree.Node(3);
            tree.rootNode.leftChild.leftChild = new BinaryTree.Node(7);
            tree.rootNode.leftChild.rightChild = new BinaryTree.Node(6);
            tree.rootNode.rightChild.leftChild = new BinaryTree.Node(5);
            tree.rootNode.rightChild.rightChild = new BinaryTree.Node(4);

            System.out.println("ZigZag Order traversal of binary tree is");
            // tree.printZigZag();
            // 1 3 2 7 6 5 4
            tree.zigzag();
        }
}
