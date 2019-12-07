package algorithm.inflearn.a.stringarray;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosest {
    public static void main(String[] args) {
        int[][] arr1 = {{1,3},{-2,2}};
        int k1 = 1;
        // output {{-2,2}}
        solve(arr1, k1);
    }

    private static void solve(int[][] arr, int k) {
        Queue<int[]> q = new PriorityQueue<>((int[] a,int[] b) -> (a[0]^2 + a[1]^2) - (b[0]^2 + b[1]^2) );
        for(int[] a : arr) {
            q.offer(a);
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = q.poll();
        }

        for (int[] res : result) {
            System.out.println(res[0] + " " + res[1]);
        }
    }
}
