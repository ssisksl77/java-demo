package algorithm.meilprogramming;


import java.util.Arrays;
import java.util.OptionalInt;

/**
 정수로된 배열이 주어지면, 각 원소가 자신을 뺀 나머지 원소들의 곱셈이 되게하라.
 단, 나누기 사용 금지, O(n) 시간복잡도
 Given an integer array, make each element a product of all element values without itself.

 예제)
 input: [1, 2, 3, 4, 5]
 output: [120, 60, 40, 30, 24]
 */
public class Test10 {
    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5};
        solve(ints);
    }

    private static void solve(int[] ints) {
        OptionalInt sum = Arrays.stream(ints).reduce((a, b) -> a * b);
        sum.getAsInt();

        int[] res = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            res[i] = sum.getAsInt() / ints[i];
        }

        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
