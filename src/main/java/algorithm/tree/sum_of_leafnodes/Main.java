package algorithm.tree.sum_of_leafnodes;
// https://www.geeksforgeeks.org/sum-leaf-nodes-minimum-level/
public class Main {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.left = new Node(8);
        root.right.left.right = new Node(9);

        System.out.println(Node.sumOfLeafNodesAtMinimumLevel(root));
    }
}
