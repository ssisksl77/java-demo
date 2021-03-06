package algorithm.meilprogramming;

/**
 중복된 원소가 없는 정렬된 배열이 있습니다. 이 배열에서 원소의 값이 원소의 인덱스 값과 같다면 프린트 하시오. 시간복잡도 O(log n).
 Given a sorted array of unique values, find an element where its value is equal to the index.

 input: [-30, 1, 4, 60]
 output: 1 // input[1] = 1

 input: [0, 3, 10, 60]
 output: 0 // input[0] = 0

 input: [-40, -30, -20, 3]
 output: 3 // input[3] = 3
 */
public class Test23 {

    public static void main(String[] args) {
        // output 1
        int[] arr1 = {-30, 1, 4, 60};
        solve(arr1);

        // output 2
        int[] arr2 = {0,3,10,60};
        solve(arr2);

        int[] arr3 = {-40,-30,-20,3};
        solve(arr3);

    }

    private static void solve(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if (i == arr[i]) {
                System.out.println(i);
                return;
            }
        }
    }
}
