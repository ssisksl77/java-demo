package algorithm.programmers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Printer2 {

	public static void main(String[] args) {
		class Doc implements Comparable<Doc> {
			int prior;
			int loc;

			public Doc(int prior, int loc) {
				this.prior = prior;
				this.loc = loc;
			}

			public int compareTo(Doc o) {
				return prior - o.prior;
			}
			public String toString() {
				return "Doc [prior=" + prior + ", loc=" + loc + "]";
			}
		}
		
		int[] priorities = {2, 1, 3, 2};
		int location = 2;

		PriorityQueue<Doc> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < priorities.length; i++) {
			pq.add(new Doc(priorities[i], i));
//			System.out.println(new Doc(priorities[i], i));
		}
		int answer = 1;
		while(!pq.isEmpty()) {
			for (int i = 0; i < priorities.length; i++) {
				if (pq.peek().prior == priorities[i]) {
					if (pq.peek().loc == location) {
						System.out.println(pq.peek());
						System.out.println(answer);
						return;
					} else {
						pq.poll();
						answer++;	
					}
					
				}
			}
		}
	}
}