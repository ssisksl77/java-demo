package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.List;

/**
2차 정수 배열(2D int array)가 주어지면, 소용돌이 모양으로 원소들을 프린트하시오. 예제를 보시오.
Given a 2D integer array, print all elements in a circular spiral shape starting from [0][0]. See example.

예제)
﻿input:
	[[1, 2, 3],
	[8, 9, 4],
	[7, 6, 5]]
 */
public class Test17 {
	
	public static void main(String[] args) {
		int[][] mat = {{1,2,3}
					  ,{8,9,4}
					  ,{7,6,5}};
		solve(mat);
	}

	private static void solve(int[][] mat) {
		int[][] direction = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		int dIdx = 0;
		
		boolean[][] visited = new boolean[mat.length][mat[0].length];
		List<Integer> list = new ArrayList<>();
		list.add(mat[0][0]);
		visited[0][0] = true;
		
		int i = 1;
		int cnt = mat.length * mat[0].length;
		int x = 0;
		int y = 0;
		while(i < cnt) {
			int dx = x + direction[dIdx][0];
			int dy = y + direction[dIdx][1];
			
			if (dx < 0  || dy < 0 || dx >= mat.length || dy >= mat[0].length || visited[dx][dy]) {
				dIdx = (dIdx+1) % 4;
				dx = x + direction[dIdx][0];
				dy = y + direction[dIdx][1];
			}
			
			list.add(mat[dx][dy]);
			
			x = dx;
			y = dy;
			i++;
			visited[x][y] = true;
		}
		
		for(Integer n : list) {
			System.out.print(n + " ");
		}
	}
}
