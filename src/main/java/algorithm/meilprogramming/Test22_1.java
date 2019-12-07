package algorithm.meilprogramming;

public class Test22_1 {

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
        
        Node sortedList = sortedMerge(left, right);
        return sortedList;
    }

    private static Node sortedMerge(Node left, Node right) {
        Node mergedNode;
        if (left == null) return right;
        if (right == null) return left;

        if (left.val < right.val) {
            mergedNode = left;
            mergedNode.next = sortedMerge(left.next, right);
        } else {
            mergedNode = right;
            mergedNode.next = sortedMerge(left, right.next);
        }
        return mergedNode;
    }

    private static Node getHalf(Node root) {
        if (root == null) return null;
        Node slowPointer = root;
        Node fastPointer = root.next;

        while(fastPointer != null) {
            fastPointer = fastPointer.next;
            if (fastPointer != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }
        }
        return slowPointer;
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
