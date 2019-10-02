package algorithm.programmers;

import java.util.*;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 * @author yuhnam
 *
 */
public class Printer {
	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;

		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		for (Integer p : priorities) {
			q.add(p);
		}
		
		int answer = 1;
		while(!q.isEmpty()) {
			for (int i = 0; i < priorities.length; i++) {
				if (priorities[i] == q.peek()) {  // 정렬된 값(peek)과 매칭되는 첫번째 priority를 찾음
					if (i == location) {  // 찾고자 하는 index인지 확인
						System.out.println(answer);// 찾고자 하는 녀석이면,
						return;
					}
					else {
						q.poll();
						answer++;
					}
				}
			}
		}
	}

}
