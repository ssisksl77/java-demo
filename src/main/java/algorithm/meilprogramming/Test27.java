package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 단방향 연결 리스트(singly linked list)가 주어지면 총 합이 0으로 되는 연결된 노드들을 뺀 뒤 남은 노드의 값을 프린트 하시오.
 * Given a linked list, remove consecutive nodes that sum to zero.
 * Print the values of leftover nodes.
 * 
 * input : 3 -> (-5) -> 5 -> 1 -> 2 -> 3
 * output : 3 -> 1 -> 2 -> 3
 * 
 * input : 1 -> 2 -> 3 -> 4 -> (-10) -> 5
 * output : 5
 * 
 * input : 10 -> (-3) -> (-4) -> (-3) -> 1
 * output : 1
 * @author yuhnam
 *
 */
public class Test27 {
	public static void main(String[] args) {
		Node node1 = new Node(3, new Node(-5, new Node(5, new Node(1, new Node(2, new Node(3)))))); // );
		solve(node1);
		
	}
	
	private static void solve(Node node) {
		List<Integer> list = new ArrayList<>();
		Node currentNode = node;
		while(currentNode != null) {
			list.add(currentNode.val);
			currentNode = currentNode.next;
		}
		// 지렁이게임을 생각하자.
		
		int sum = list.get(0);
		for(int head = 1, tail = 0; head < list.size();) {
			if (head == tail) {
				head++;
			}
			if (head >= 0) {
				head++;
			} else {
				tail++;
			}
		}
		
		System.out.println(list);
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
		
	}
}
