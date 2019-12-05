package algorithm.meilprogramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 0과 1로 만들어진 2D 정수 배열이 있습니다.
 0은 장애물이고 1은 도로일때, 두 좌표가 주어지면, 첫번째 좌표에서 두번째 좌표까지 가장 가까운 거리를 구하시오.
 두 좌표는 모두 도로에서 시작되고 좌, 우, 아래, 위로 움직이며 대각선으로는 움직일 수 없습니다.
 만약 갈 수 없다면 -1을 리턴하시오.

 Given a 2D array with 0s and 1s, 0 represents an obstacle and 1 represents a road.
 Find the closest distance between two given points.
 You must only move up down right left.
 You cannot move through an obstacle.

 예제)
 Input:
 {{1, 0, 0, 1, 1, 0},
  {1, 0, 0, 1, 0, 0},
  {1, 1, 1, 1, 0, 0},
  {1, 0, 0, 0, 0, 1},
  {1, 1, 1, 1, 1, 1}}
 Start: (0, 0)
 Finish: (0, 4)

 Output: 8
 */
public class Test15 {
    public static void main(String[] args) {
        int[][] input =  {{1, 0, 0, 1, 1, 0},
                          {1, 0, 0, 1, 0, 0},
                          {1, 1, 1, 1, 0, 0},
                          {1, 0, 0, 0, 0, 1},
                          {1, 1, 1, 1, 1, 1}};
        int[] start = {0,0};
        int[] finish = {0,4};

        int count = solve(input, Point.of(start[0], start[1]), Point.of(finish[0], finish[1]));
        System.out.println(count);
    }

    private static final int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    private static int solve(int[][] map, Point src, Point dest) {
        if (map[src.x][src.y] == 0 || map[dest.x][dest.y] == 0) {
            return -1;
        }

        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(src, 0));
        visited[src.x][src.y] = true;

        while(!q.isEmpty()) {
            Node currentNode = q.poll();
            Point pt = currentNode.pt;
            if (pt.x == dest.x && pt.y == dest.y) return currentNode.dist;

            for (int i = 0 ; i < directions.length; i++) {
                int row = pt.x + directions[i][0];
                int col = pt.y + directions[i][1];

                if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) continue;
                if (map[row][col] == 0 || visited[row][col] == true) continue;
                visited[row][col] = true;
                Node neighbor = new Node(Point.of(row, col), currentNode.dist+1);
                q.offer(neighbor);
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

        public static Point of(int x, int y) {
            return new Point(x, y);
        }

        @Override public boolean equals(Object obj) {
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }
    }


    private static class Node {
        Point pt;
        int dist;

        public Node(Point pt, int dist) {
            this.pt = pt;
            this.dist = dist;
        }
    }
}
