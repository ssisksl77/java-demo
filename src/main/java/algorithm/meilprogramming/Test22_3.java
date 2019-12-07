package algorithm.meilprogramming;

public class Test22_3 {
    public static void main(String[] args) {
        Node root = new Node(3, new Node(2, new Node(6, new Node(1, null))));
        Node res = mergeSort(root);
        res.print();
    }

    private static Node mergeSort(Node root) {
        if (root == null) return null;
//        System.out.println();
//        root.print();
        if (root.next == null) return root;

        Node half = getHalf(root);
        Node nextOfHalf = half.next;
        half.next = null;

        Node left = mergeSort(root);
        Node right = mergeSort(nextOfHalf);

        Node sortedRoot = sortedMerge(left, right);

        return sortedRoot;
    }

    private static Node sortedMerge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node newNode;
        if (left.val < right.val) {
            newNode = left;
            newNode.next = sortedMerge(left.next, right);
        } else {
            newNode = right;
            newNode.next = sortedMerge(left, right.next);
        }

        return newNode;
    }

    private static Node getHalf(Node root) {
        Node slowPtr = root;
        Node fastPtr = root.next;

        while(fastPtr != null) {
            fastPtr = fastPtr.next;
            if(fastPtr != null) {
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
