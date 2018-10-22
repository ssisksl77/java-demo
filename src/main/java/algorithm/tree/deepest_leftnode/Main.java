package algorithm.tree.deepest_leftnode;
// https://www.geeksforgeeks.org/deepest-left-leaf-node-binary-tree-iterative-approach/
// 가장 깊이 있는 왼쪽 리프노드의 값을 구한다.
public class Main {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.right = new Node(7);
        root.right.right.left = new Node(8);
        root.right.left.right.left = new Node(9);
        root.right.left.right.right = new Node(10);



        System.out.println(Node.getDeepestLeftLeafNode(root).data);  // 9


        root.left.left.left = new Node(10);
        root.left.left.left.left = new Node(11);
        root.left.left.left.left.left = new Node(13);

        System.out.println(Node.getDeepestLeftLeafNode(root).data);  // 13
    }
}
