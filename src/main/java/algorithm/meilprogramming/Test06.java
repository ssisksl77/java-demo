package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 간격(interval)로 이루어진 배열이 주어지면, 겹치는 간격 원소들을 합친 새로운 배열을 만드시오. 
 * 간격은 시작과 끝으로 이루어져 있으며 시작은 끝보다 작거나 같습니다.
Given a list of intervals, merge intersecting intervals.

예제)
Input: {{2,4}, {1,5}, {7,9}}
Output: {{1,5}, {7,9}}

Input: {{3,6}, {1,3}, {2,4}}
Output: {{1,6}}
 */
public class Test06 {
	
	public static void main(String[] args) {
		int[][] input1 = {{2,4}, {1,5}, {7,9}};
		int[][] input2 = {{3,6}, {1,3}, {2,4}};
		
		int[][] res = solve(input1);
		// int[][] res2 = solve(input2);
	}

	private static int[][] solve(int[][] input) {
		List<int[]> pairs = new ArrayList<>();
		for (int[] i : input) {
			pairs.add(i);
		}
		List<int[]> res = new ArrayList<>();
		for(boolean done = false; done == false; done = true) {
			res = new ArrayList<>();
			for (int i = 1 ; i < pairs.size(); i++) {
				// 둘중 하나의 END가 START보다 크거나 같으면 겹침.
				if( pairs.get(i-1)[1] >= pairs.get(i)[0] || pairs.get(i)[1] >= pairs.get(i-1)[0]) {
					done = false;
					int min = Math.min(Math.min(pairs.get(i-1)[0], pairs.get(i)[0]), Math.min(pairs.get(i-1)[1], pairs.get(i)[1]));
					int max = Math.max(Math.max(pairs.get(i-1)[0], pairs.get(i)[0]), Math.max(pairs.get(i-1)[1], pairs.get(i)[1]));
					res.add(new int[] { min, max });
				}
				res.add(new int[] {pairs.get(i)[0], pairs.get(i)[1]});
			}
			pairs = new ArrayList<>(res);
		}
		
		for (int[] i : res) {
			System.out.println(i[0] +" " + i[1]);
		}
		
		return null;
	}
}
