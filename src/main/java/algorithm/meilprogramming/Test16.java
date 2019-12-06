package algorithm.meilprogramming;

/**
이진 트리를 루트 노드를 기준으로 좌우반전 하시오.
이 문제는 구글이 Homebrew 창시자에게 낸 문제로 유명합니다.

Given a binary tree root node, reverse the tree horizontally.
 */
public class Test16 {
	
	public static void main(String[] args) {
		Node root = new Node(0, new Node(1, new Node(2), new Node(3)), new Node(4, new Node(5), new Node(10)));
		root.print();
		System.out.println();
		solve(root);
		root.print();
	}
	
	private static void solve(Node root) {
		if (root == null) return;
		
		Node tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		
		solve(root.left);
		solve(root.right);
	}

	private static class Node {
		int val;
		Node left;
		Node right;
		
		public Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		public Node(int val) {
			this.val = val;
		}
		
		void print() {
			System.out.print(this.val);
			if (this.left != null) {
				System.out.print("-> left.");
				this.left.print();
			}
			if (this.right != null) {
				System.out.print("-> right.");
				this.right.print();
			}
		}
	}
}
