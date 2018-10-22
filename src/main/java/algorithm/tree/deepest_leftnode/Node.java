package algorithm.tree.deepest_leftnode;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    int data;
    Node left;
    Node right;

    Node (int data) {
        this.data = data;
    }

    static Node getDeepestLeftLeafNode(Node root) {
        // 가장 깊게 있으면서 왼쪽에 있는 노드.
        // bfs를 이용한다.
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node curNode = null;
        Node curLeft = null;
        Node curRight = null;
        Node result = null;
        while(!q.isEmpty()) {
            curNode = q.poll();
            curLeft = curNode.left;
            curRight = curNode.right;

            if (curLeft != null) {
                result = curLeft;
                q.add(curLeft);
            }

            if (curRight != null) q.add(curRight);

        }

        return result;
    }
}

