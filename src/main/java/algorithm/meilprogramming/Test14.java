package algorithm.meilprogramming;

/**
두개의 정렬된(sorted) 정수 링크드리스트(linked list)가 ㅜ어지면, 두 리스트를 합친 정렬된 링크드리스트를 만드시오.
Given two sorted integer linked lists, merge the two linked lists. Merged linked list must also be sorted.

예제)
Input: 1->2->3, 1->2->3
Output: 1->1->2->2->3->3

Input: 1->3->5->6, 2->4
﻿Output: 1->2->3->4->5->6
 */
public class Test14 {
	static class Node {
		int val;
		Node next;

		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
		
		public static Node of(int val, Node next) {
			return new Node(val, next);
		}
		
		public static Node of(int val) {
			return new Node(val, null);
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
	
	public static void main(String[] args) {
		// Input: 1->2->3, 1->2->3
		//Output: 1->1->2->2->3->3
		Node n1 = Node.of(1, Node.of(2, Node.of(3)));
		Node n2 = Node.of(1, Node.of(2, Node.of(3)));
		Node res = solve(n1, n2);
		res.print();
	}
	
	private static Node solve(Node n1, Node n2) {
		Node root = Node.of(n1.val); // 혹은 Node.of(0)을 만든다음에 리턴할 때  root.next로 해도댐.
		Node currentNode = root;
		n1 = n1.next;
		
		while(n1 != null && n2 != null) {
			if (n1.val > n2.val) {
				currentNode.next = Node.of(n2.val);
				n2 = n2.next;
			} else {
				currentNode.next = Node.of(n1.val);
				n1 = n1.next;
			}
			currentNode = currentNode.next;
			
		}
		
		while (n1 != null) {
			currentNode.next = Node.of(n1.val);
			currentNode = currentNode.next;
			n1 = n1.next;
		}
		
		while (n2 != null) {
			currentNode.next = Node.of(n2.val);
			currentNode = currentNode.next;
			n2 = n2.next;
		}
		
		return root;
	}
}

