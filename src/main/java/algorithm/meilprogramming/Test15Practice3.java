package algorithm.meilprogramming;
import java.util.*;
public class Test15Practice3 {
	public static void main(String[] args) {
        int[][] input =  {{1, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}};
		int[] start = {0,0};
		int[] finish = {0,4};
		
		int count = solve(input, new Point(start[0], start[1]), new Point(finish[0], finish[1]));
		System.out.println(count);
	}
	
	private static final int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
	private static int solve(int[][] input, Point start, Point end) {
		if (input[end.x][end.y] == 0 ) return -1;
		
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		boolean[][] visited = new boolean[input.length][input[0].length];
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			Point p = node.p;
			if (p.x == end.x && p.y == end.y) return node.distance;
			
			for (int i = 0; i < dirs.length; i++) {
				int dx = p.x + dirs[i][0];
				int dy = p.y + dirs[i][1];
				if (dx < 0 || dy < 0 || dx >= input.length || dy >= input[0].length) continue;
				if (input[dx][dy] == 0 || visited[dx][dy] == true) continue;
				
				visited[dx][dy] = true;
				q.offer(new Node(new Point(dx, dy), node.distance+1));
			}
		}
		return -1;
	}

	private static class Point {
		int x; 
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Node {
		Point p;
		int distance;
		
		public Node(Point p, int distance) {
			this.p = p;
			this.distance = distance;
		}
	}
	
}
