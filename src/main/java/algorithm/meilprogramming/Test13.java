package algorithm.meilprogramming;

/**
 링크드 리스트(linked list)의 머리 노드(head node)와 정수 N이 주어지면, 끝에서 N번째 노드(node)를 제거하고 머리 노드(head node)를 리턴하시오.
 단, 리스트를 한번만 돌면서 풀어야합니다. N은 리스트 길이보다 크지 않습니다.

 Given a head node of a singly linked list, remove the Nth last element and return the head node.

 예제)
 Input: 1->2->3->4->5, N=2
 Output: 1->2->3->5

 Input: 1->2->3, N=3
 Output: 2->3

 Input: 1, N=1
 Output: null
 */
class Node {
    int val;
    Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
    
    void print() {
        System.out.print(val);
        if (next != null) {
            System.out.print(" -> ");
            next.print();
        } else {
            System.out.println();
        }
    }
}
public class Test13 {

    public static void main(String[] args) {
        Node n1 = new Node(5, null);
        Node n2 = new Node(4, n1);
        Node n3 = new Node(3, n2);
        Node n4 = new Node(2, n3);
        Node root = new Node(1, n4);
        int N = 2;
//        root.print();
        Node res = solve(root, N);
        res.print();
        
        
        // Input: 1->2->3, N=3
        // Output: 2->3
        Node n13 = new Node(3, null);
        Node n12 = new Node(2, n13);
        Node n11 = new Node(1, n12);
        Node res2 = solve(n11, 3);
        res2.print();
        
        // Input: 1, N=1
        // Output: null
        Node n21 = new Node(1, null);
        Node res3 = solve(n21, 1);
        System.out.println(res3);
        
    }

    private static Node solve(Node root, int N) {
        Node node1 = root;
        Node node2 = root;
        if (root == null) return null;
        
        while(N > 0) {
            node2 = node2.next;
            N--;
        }
        
        if (node2 == null) {
        	root = root.next;
        	return root;
        }
        
        while(node2.next != null) {
            node1 = node1.next;
            node2 = node2.next;
        }

        node1.next = node1.next.next;
        return root;

    }
}
