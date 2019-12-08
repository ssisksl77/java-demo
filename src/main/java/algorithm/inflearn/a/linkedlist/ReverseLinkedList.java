package algorithm.inflearn.a.linkedlist;

/**
 * Reverse a singly linked list.
 *
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        Node root = new Node(1, new Node(2, new Node (3)));
        Node res = solve(root);
    }

    private static Node solve(Node cNode) {
        Node prev = null;
        Node next = null;
        while(cNode != null) {
            next = cNode.next;
            cNode.next = prev;
            prev = cNode;
            cNode = next;
        }
        return prev;
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

        void print() {
            System.out.print(val);
            if(next != null) {
                System.out.print("->");
                next.print();
            }
        }
    }

}
