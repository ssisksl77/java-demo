package algorithm;

/**
 * 갯수 제한 없는 배낭문제
 * n = 3
 * (w, v) = {(3, 4), (4, 5), (2, 3)}
 * W = 7
 *
 * output 10 (0번 1개, 2번 2개)
 * dp[i][j] := i번까지의 물건 무게의 총합이 j가 되도록 골랐을 때 가격 총합의 최대치 점화식
 *
 * dp[0][j] = 0
 * dp[i][j] = max {dp[i-1][j-k*w[i]] + k*v[i] | 0<=k}  : k는 해당 물건을 몇개 고를 건지??
 */
public class Knapsack2 {

    int n = 3;
    int[] w = {3, 4, 2};
    int[] v = {4, 5, 3};
    int W = 7;

    void solveMyself() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                for (int k = 0; k * w[i] <= j; k++) {

                }
            }
        }
    }
}
