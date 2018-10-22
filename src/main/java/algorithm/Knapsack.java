package algorithm;

import java.util.Arrays;

public class Knapsack {

    static final int MAX_N = 100;
    static int[][] dp = new int[MAX_N][MAX_N];
    static int W;
    static int[] v;
    static int[] w;
    static int n;

    public static void main(String[] args) {

        n = 4;
        w = new int[] {2, 1, 3, 2};
        v = new int[] {3, 2, 4, 2};
        W = 5;
        // Arrays.fill(dp, -1);  error!

        // int res = solve(0,W);
        // System.out.println(res);
        // solve2();
        solve3();

    }

    private static int solve(int idx, int value) {  // weight가 아닌 v인듯 하다.
        for (int[] row : dp)
            Arrays.fill(row, -1);

        if (dp[idx][value] >= 0) {
            return dp[idx][value];
        }
        int res;
        if (idx == n) {
            res = 0;
        } else if ( value < w[idx]) {  // skip
            res = solve(idx+1, value);
        } else {  // 넣는 경우, 넣지 않는 경우.
            int a = solve(idx + 1, value);
            int b = solve(idx + 1, value - w[idx]) + v[idx];  // 이 경우에만 물건을 넣는 경우 v[idx]로 이때만 추가된다.
            res = Math.max(a, b);
        }

        return dp[idx][value] = res;
    }


    /**
     * dp[n][j] = 0
     * dp[i][j] = dp[i+1][j] (j < w[i])
     *          = max(dp[i+1][j], dp[i+1][j-w[i] + v[i]) ...
     * 이렇게 점화식을 이용하면 재귀함수를 사용하지 않고 계산할 수 있다.
     */
    private static void solve2() {  // i=idx, j=weight
        for (int[] row : dp)
            Arrays.fill(row, 0);  // -1이 아님을 주의. 캐시를 하는 것이 아니라. 전부 더할 것이다.

        for (int i = n - 1; i >= 0; i--) {  // 역방향으로 루프를 돈다. n개 남았는지 확인하는 듯. 하지만 헷갈린다.
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j - w[i]] + v[i]);
                }
            }
        }

        for (int i = 0; i <= W; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[0][W]);
    }


    /**
     * dp[i+1][j]:= i번째까지의 물건 무게의 총합이 j이하가 되도록 골랐을 경우의 가격 총합의 최대치
     * dp[0][j]=0
     * dp[i][j]=dp[i][j] (j < w[i])
     *         =max(dp[i][j], dp[i][j-w]+v[i]) (그 외)
     */
    private static void solve3() {
        for (int[] row : dp) Arrays.fill(row, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    dp[i+1][j] = dp[i][j];
                } else {
                    int a = dp[i][j];
                    int b = dp[i+1][j - w[i] + v[i]];  // w[i]는 해당 무게를 제외한 value를 구하기 위함!
                    dp[i+1][j] = Math.max(a,b);
                }
            }
        }
        System.out.println(dp[n][W]);
    }
}
