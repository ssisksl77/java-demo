package algorithm.tree.sum_of_leafnodes;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    static int sumOfLeafNodesAtMinimumLevel(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node curNode;
        int res = 0;
        boolean isLeafLevel = false;
        while(!q.isEmpty()) {
            curNode = q.poll();

            if (curNode.left == null && curNode.right == null) {
                res += curNode.data;
                break;
            }
            if (curNode.left != null) { q.add(curNode.left); }
            if (curNode.right != null) { q.add(curNode.right);}
        }

        while(!q.isEmpty()) {
            // System.out.println(res);
            curNode = q.poll();
            if (curNode.left == null && curNode.right == null)
              res += curNode.data;
        }
        return res;
    }
}