package algorithm.meilprogramming;

import java.util.Stack;

/**
 * 이진탐색트리안에 X보다 크고 Y보다 작은 모든 노드 값을 프린트 하시오.
 * Given a binary search tree, print all node values that are bigger than X and smaller than Y.
 */
public class Test25 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(6, new TreeNode(4, new TreeNode(3), new TreeNode(5)), new TreeNode(9, new TreeNode(7), new TreeNode(10)));
		
		int X = 3;
		int Y = 8;
		solve(root, X, Y);
	}
	
	private static void solve(TreeNode root, int x, int y) {
		if (root == null) return;
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode treeNode = stack.pop();
			if (x < treeNode.val && treeNode.val < y) {
				System.out.println(treeNode.val);
			}
			
			if (treeNode.left != null) stack.push(treeNode.left);
			if (treeNode.right != null) stack.push(treeNode.right);
		}
		
		
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
		public TreeNode(int val) {
			this.val = val;
		}
		
	}
}
