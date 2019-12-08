package algorithm.inflearn.a.stringarray;

public class TrapingRainWater {
    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        solve(nums);
    }

    private static void solve(int[] heights) {
        if (heights == null || heights.length <= 2) {
            System.out.println(0);
            return;
        }

        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        int max = heights[0];
        left[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (max >= heights[i]) {
                left[i] = max;
            } else {
                max = heights[i];
                left[i] = heights[i];
            }
        }
        for(int i : left) {
            System.out.print(i + " ");
        }
        System.out.println();
        max = heights[heights.length-1];
        right[right.length-1] = max;
        for (int i = heights.length-2; i >= 0; i--) {
            if (max >= heights[i]) {
                right[i] = max;
            } else {
                max = heights[i];
                right[i] = heights[i];
            }
        }
        for(int i : right) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] sum = new int[left.length];
        for (int i = 0; i < left.length; i++) {
            sum[i] = Math.min(left[i], right[i]);
        }

        for(int i : sum) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < sum.length; i++) {
            sum[i] -= heights[i];
        }

        int result = 0;
        for(int i : sum) {
            result += i;
        }
        System.out.println(result);
    }
}
