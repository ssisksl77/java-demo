package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
		
		solve(input1);
		solve(input2);
	}

	private static void solve(int[][] input) {
		Arrays.sort(input, new Comparator<int[]>(){
			@Override public int compare(int[] o1, int[] o2) {
				return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
			}
		});
		List<int[]> res = new ArrayList<>();
		res.add(new int[] {input[0][0], input[0][1]});
		for (int i = 1; i < input.length; i++) {
			if (res.get(res.size()-1)[1] >= input[i][0]) {
				res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], input[i][1]);
			} else {
				res.add(input[i]);
			}
		}
		
		for (int[] a : res) {
			System.out.println(a[0] + " " + a[1]);
		}
	}
}
