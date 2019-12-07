package algorithm.inflearn.a.stringarray;

public class MaximumSubArray {
    public static void main(String[] args) {
        // subarray 중 합이 제일 큰 수
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        solve(arr);
//        solve2(arr);
        int[] arr2 = {3, -2, 3};
        solve(arr2);
    }

    // 인프런 답안
    private static void solve(int[] arr) {
        int newSum = arr[0];
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            // 혼자 있는게 값 vs 합친값.
            newSum = Math.max(arr[i], newSum + arr[i]); // -1 vs 3-1
            max = Math.max(newSum, max);
        }
        System.out.println(max);
    }
}
