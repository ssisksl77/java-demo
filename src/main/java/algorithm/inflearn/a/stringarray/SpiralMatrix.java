package algorithm.inflearn.a.stringarray;

import java.util.ArrayList;
import java.util.List;

/**
 Given a matrix of m x n elements (m rows, n columns),
 return all elements of the matrix in spiral order.
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}
                         ,{4,5,6}
                         ,{7,8,9}};
        solve(matrix);
    }

    private static void solve(int[][] matrix) {
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        List<Integer> list = new ArrayList<>();
        list.add(matrix[0][0]);
        visited[0][0] = true;
        for (int i = 1, x = 0, y = 0, dir = 0; i < matrix.length*matrix[0].length;) {
            int dx = x + direction[dir][0];
            int dy = y + direction[dir][1];

            if (dx < 0 || dy < 0 || dx >= matrix.length || dy >= matrix[0].length || visited[dx][dy]) {
                dir = (dir+1) % 4;
                dx = x + direction[dir][0];
                dy = y + direction[dir][1];
            }

            i++;
            x = dx;
            y = dy;
            visited[dx][dy] = true;
            list.add(matrix[dx][dy]);
        }
        System.out.println(list);
    }
}
