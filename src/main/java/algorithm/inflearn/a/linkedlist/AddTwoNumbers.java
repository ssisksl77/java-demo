package algorithm.inflearn.a.linkedlist;

public class AddTwoNumbers {
	
	public static void main(String[] args) {
		Node root1 = new Node(2, new Node(4, new Node(3, null)));
		Node root2 = new Node(5, new Node(6, new Node(2, null)));
//		Node n = solve(root1, root2);
//		n.print();
		Node n = solve2(root1, root2);
		n.print();
	}
	
	private static Node solve(Node root1, Node root2) {
		Node node = new Node(0);
		Node currentNode = node;
		
		int carry = 0;
		while(root1 != null && root2 != null) {
			int newValue = root1.val + root2.val;
			currentNode.next = new Node((newValue + carry) % 10);
			
			carry = (newValue + carry) / 10;
			
			currentNode = currentNode.next;
			root1 = root1.next;
			root2 = root2.next;
		}

		return node.next;
	}

	// 답
	private static Node solve2(Node n1, Node n2) {
		Node newHead = new Node(0);
		Node p1 =n1, p2 =n2 , p3=newHead;
		int carry =0;

		while(p1 != null|| p2!= null) {
			if(p1 != null) {
				carry+= p1.val;
				p1 =p1.next;
			}
			if(p2 != null) {
				carry += p2.val;
				p2 = p2.next;
			}
			p3.next = new Node(carry%10);
			p3 =p3.next;
			carry /= 10;
		}
		if(carry==1) p3.next = new Node(1);
		return newHead.next;
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
		public void print() {
			System.out.println(val);
			if (next != null) {
				next.print();
			}
		}
	}
}
