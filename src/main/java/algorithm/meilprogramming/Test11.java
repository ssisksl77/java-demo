package algorithm.meilprogramming;

import java.util.Arrays;

/**
 정수 배열(int array)과 정수 N이 주어지면, N번째로 큰 배열 원소를 찾으시오.
 Given an integer array and integer N, find the Nth largest element in the array.

 예제)
 Input: [-1, 3, -1, 5, 4], 2
 Output: 4

 Input: [2, 4, -2, -3, 8], 1
 Output: 8

 Input: [-5, -3, 1], 3
 Output: -5
 */
public class Test11 {
    public static void main(String[] args) {
        int[] a1 = {-1,3,-1,5,4};
        int a2 = 2;

        int[] a3 = {2,4,-2,-3,8};
        int a4 = 1;

        int[] a5 = {-5,-3,1};
        int a6 = 3;

        solve(a1, a2);
        System.out.println(solve2(a1, a2));
    }


    /**
     *  이렇게 푸는거 아니라고 함.
     */
    private static void solve(int[] arr, int n) {
        Arrays.sort(arr);

        System.out.println(arr[arr.length-n]);
    }

    /**
     * quick sort로 필요한 부분만 소팅을 하는 것이다.
     */
    private static int solve2(int[] arr, int k) {
        return quickselect(arr, 0, arr.length - 1, k - 1);
    }


    /**
     *
     */
    private static int quickselect(int[] arr, int first, int last, int k) {
        if (first <= last) {
            int pivot = partition(arr, first, last);
            if (pivot == k) {
                return arr[k];
            }
            if (pivot > k) {
                return quickselect(arr, first, pivot-1, k);
            }
            return quickselect(arr, pivot+1, last, k);
        }
        return Integer.MIN_VALUE;
    }

    private static int partition(int[] arr, int first, int last) {
        int pivotSpot = first;
        for (int i = first; i < last; i++) {
            if (arr[i] > arr[last]) {
                awap(arr, i, pivotSpot);
                pivotSpot++;
            }
        }
        awap(arr, pivotSpot, last);
        return pivotSpot;
    }

    private static void awap(int[] arr, int i, int pivotSpot) {
        int tmp = arr[i];
        arr[i] = arr[pivotSpot];
        arr[pivotSpot] = tmp;
    }
}
