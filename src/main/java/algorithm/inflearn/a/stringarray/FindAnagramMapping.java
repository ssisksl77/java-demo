package algorithm.inflearn.a.stringarray;

import java.util.HashMap;
import java.util.Map;

public class FindAnagramMapping {
    public static void main(String[] args) {
        // A값이 B의 어디에 있는지 보는 것.
        int[] A = {12, 28, 46, 32, 50};
        int[] B = {50, 12, 32, 46, 28};
        // output [1,4,3,2,0]
        solve(A, B);
    }

    private static void solve(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = map.get(arr1[i]);
        }

        for(int i : res) {
            System.out.print(i + " ");
        }
    }
}
