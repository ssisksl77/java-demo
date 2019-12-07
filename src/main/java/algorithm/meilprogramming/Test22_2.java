package algorithm.meilprogramming;

public class Test22_2 {
    public static void main(String[] args) {
        Node root = new Node(3, new Node(2, new Node(6, new Node(1, null))));
        Node res = mergeSort(root);
        res.print();
    }

    private static Node mergeSort(Node root) {
        if (root == null || root.next == null) return root;

        Node half = getHalf(root);
        Node nextOfHalf = half.next;
        half.next = null;

        Node left = mergeSort(root);
        Node right = mergeSort(nextOfHalf);

        Node merged = merge(left, right);

        return merged;
    }

    private static Node merge(Node left, Node right) {
        Node mergedNode;
        if (left == null) return right;
        if (right == null) return left;

        if (left.val < right.val) {
            mergedNode = left;
            mergedNode.next = merge(left.next, right);
        } else {
            mergedNode = right;
            mergedNode.next = merge(left, right.next);
        }

        return mergedNode;
    }

    private static Node getHalf(Node root) {
        Node fastPtr = root.next;
        Node slowPtr = root;

        while(fastPtr != null) {
            fastPtr = fastPtr.next;
            if (fastPtr != null) {
                fastPtr = fastPtr.next;
                slowPtr = slowPtr.next;
            }
        }

        return slowPtr;
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        void print() {
            System.out.print(val);
            if (next != null) {
                System.out.print("->");
                next.print();
            }
        }
    }
}
