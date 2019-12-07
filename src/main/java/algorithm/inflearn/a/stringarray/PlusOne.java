package algorithm.inflearn.a.stringarray;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] res1 = solve(arr1);
        for (int i : res1) System.out.print(i + " ");

        System.out.println();

        int[] arr2 = {9,9,9};
        int[] res2 = solve(arr2);
        for (int i : res2) System.out.print(i + " ");

    }

    private static int[] solve(int[] arr) {
        int[] res = new int[arr.length+1];
        for (int i = 0; i < arr.length; i++) {
            res[i+1] = arr[i];
        }

        res[arr.length]++;

        for (int i = arr.length; i > 0; i--) {
            if (res[i] == 10) {
                res[i-1]++;
                res[i] = 0;
            }
        }

        if (res[0] == 0) {
            return Arrays.copyOfRange(res, 1, res.length);
        } else {
            return res;
        }
    }
}
