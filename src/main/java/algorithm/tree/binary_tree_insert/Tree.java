package algorithm.tree.binary_tree_insert;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    static class Node {
        int data; Node left; Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;
    Node temp;

    public Tree(Node root) {
        this.root = root;
        this.temp = this.root;
    }

    void inorder(Node temp) {
        if (temp == null) {
            return;
        }

        inorder(temp.left);
        System.out.print(temp.data + " ");
        inorder(temp.right);
    }

    void insert(Node temp, int data) {
        Queue<Node> q = new LinkedList<>();
        q.add(temp);

        while(!q.isEmpty()) {
            temp = q.poll();

            if (temp.left == null) {
                temp.left = new Node(data);
                break;
            }
            else if (temp.right == null) {
                temp.right = new Node(data);
                break;
            }
            else {
                q.add(temp.right); // 여기서 크기를 비교해서 temp.left / temp.right를 바꿀 수 있겠다.
            }
        }
    }
}
