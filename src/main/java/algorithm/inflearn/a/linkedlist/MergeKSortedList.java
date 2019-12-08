package algorithm.inflearn.a.linkedlist;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
public class MergeKSortedList {
	public static void main(String[] args) {
		Node root1 = new Node(1, new Node(4, new Node(5)));
		Node root2 = new Node(1, new Node(3, new Node(4)));
		Node root3 = new Node(2, new Node(6));
		// output 1->1->2->3->4->4->5->6
		Node[] nodeArr = {root1, root2, root3};
//		solve(nodeArr);
		solve2(nodeArr);
	}

	// 모두 for문을 돌리면서 null 인 것을 제외하고.
	// 있는 것들중에 최소값을 구해서
	// 새로운 Node에 넣고 다음 노드로 넘어간다.
	private static void solve(Node[] nodeArr) {
		Node res = new Node(-1);
		Node cNode = res;
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		while(!isNullNode(nodeArr)) {
			for(int i = 0; i < nodeArr.length; i++) {
				if (nodeArr[i] != null && min > nodeArr[i].val) {
					min = nodeArr[i].val;
					minIdx = i;
				}
			}

			cNode.next = new Node(min);
			cNode = cNode.next;
			nodeArr[minIdx] = nodeArr[minIdx].next;

			min = Integer.MAX_VALUE;
		}
		res.next.print();
	}

	private static boolean isNullNode(Node[] nodeArr) {
		for (int i = 0; i < nodeArr.length; i++) {
			if (nodeArr[i] == null) return true;
		}
		return false;
	}

	// 해답
	private static void solve2(Node[] nodeArr) {
		Queue<Node> pq = new PriorityQueue<>((n1,n2)-> n1.val - n2.val);
		Node newHead = new Node(0);
		Node currentNode = newHead;

		// 어짜피 해드의 값만 비교하니까 계속 순서는 바뀜
		for(Node n : nodeArr) {
			pq.offer(n);
		}

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			currentNode.next = new Node(node.val);
			currentNode = currentNode.next;
			if(node.next != null) {
				pq.offer(node.next);
			}
		}
		newHead.next.print();
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
