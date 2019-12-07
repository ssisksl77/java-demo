package algorithm.meilprogramming;

/**
단방향 연결 리스트(Singly linked list)가 주어지면 O(n log n) 시간복잡도로 정렬하시오.
Given a singly linked list, sort the list in O(n log n) time complexity.
 */
public class Test22 {
	// 전혀 모르겠다.
	public static void main(String[] args) {
		Node root = new Node(3, new Node(2, new Node(6, new Node(1, null))));
		Node res = mergeSort(root);

	}
	
	private static Node mergeSort(Node root) {
		if (root == null || root.next == null) return root;
		
		Node middle = getMiddle(root);
		Node nextOfMiddle = middle.next;
		
		// 중간노드를 중심으로 두개의 리스트로 나눈다.
		middle.next = null;
		Node left = mergeSort(root);
		Node right = mergeSort(nextOfMiddle);
		
		Node sortedList = sortedMerge(left, right);
		
		return null;
	}

	private static Node sortedMerge(Node a, Node b) {
		Node result = null;
		
		if (a == null) return b;
		if (b == null) return a;
		
		if (a.val <= b.val) {
			result = a;
			result.next = sortedMerge(a.next, b);
		} else {
			result = b;
			result.next = sortedMerge(a, b.next);
		}
		return result;
	}

	private static Node getMiddle(Node root) {
		if (root == null) return null;
		
		Node fastPointer = root.next;
		Node slowPointer = root;
		
		// 빠른 포인터와 느린 포인터를 이용하여 리스트의 중간을 구한다.
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
			super();
			this.val = val;
			this.next = next;
		}
	}
}
