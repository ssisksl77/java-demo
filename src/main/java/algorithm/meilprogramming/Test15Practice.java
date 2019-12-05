package algorithm.meilprogramming;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Test15 빠르게 복기
 */
public class Test15Practice {
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


    private static final int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private static int solve(int[][] input, Point start, Point end) {
        if (input[start.x][start.y] == 0 || input[end.x][end.y] == 0) return -1;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        boolean[][] visited = new boolean[input.length][input[0].length];
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if (node.point.x == end.x && node.point.y == end.y) return node.distance;

            for (int i = 0; i < directions.length; i++) {
                int dx = node.point.x + directions[i][0];
                int dy = node.point.y + directions[i][1];

                if (dx < 0 || dy < 0 || dx >= input.length || dy >= input[0].length) continue;
                if (input[dx][dy] == 0 || visited[dx][dy] == true) continue;

                visited[dx][dy] = true;
                q.offer(new Node(new Point(dx, dy), node.distance + 1));
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
        Point point;
        int distance;

        public Node(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }


}
